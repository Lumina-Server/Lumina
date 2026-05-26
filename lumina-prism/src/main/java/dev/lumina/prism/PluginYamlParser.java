package dev.lumina.prism;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public final class PluginYamlParser {

    public PrismPluginDescriptor parse(InputStream input) throws IOException {
        Objects.requireNonNull(input, "input");

        List<Line> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String raw;
            while ((raw = reader.readLine()) != null) {
                String stripped = stripComment(raw);
                if (stripped.isBlank()) {
                    continue;
                }
                lines.add(new Line(indentOf(stripped), stripped.trim()));
            }
        }

        Map<String, Object> root = new Parser(lines).parseMap(0);
        return toDescriptor(root);
    }

    private static PrismPluginDescriptor toDescriptor(Map<String, Object> root) {
        String name = asString(root.get("name"));
        String version = asString(root.get("version"));
        String main = asString(root.get("main"));
        String website = asString(root.get("website"));
        String author = asString(root.get("author"));
        String description = asString(root.get("description"));
        String apiVersion = asString(root.get("api-version"));

        List<String> depend = asStringList(root.get("depend"));
        List<String> softDepend = asStringList(root.get("softdepend"));
        List<String> loadBefore = asStringList(root.get("loadbefore"));

        Map<String, PrismCommandDescriptor> commands = new LinkedHashMap<>();
        Object commandsNode = root.get("commands");
        if (commandsNode instanceof Map<?, ?> map) {
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                String commandName = String.valueOf(entry.getKey());
                Map<String, Object> commandMap = asMap(entry.getValue());
                commands.put(commandName, new PrismCommandDescriptor(
                        commandName,
                        asString(commandMap.get("description")),
                        asString(commandMap.get("usage")),
                        asStringList(commandMap.get("aliases"))
                ));
            }
        }

        Map<String, PrismPermissionNode> permissions = new LinkedHashMap<>();
        Object permissionsNode = root.get("permissions");
        if (permissionsNode instanceof Map<?, ?> map) {
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                String permName = String.valueOf(entry.getKey());
                permissions.put(permName, toPermissionNode(permName, asMap(entry.getValue())));
            }
        }

        return new PrismPluginDescriptor(
                name,
                version,
                main,
                website,
                author,
                description,
                apiVersion,
                depend,
                softDepend,
                loadBefore,
                commands,
                permissions,
                root
        );
    }

    private static PrismPermissionNode toPermissionNode(String name, Map<String, Object> map) {
        String description = asString(map.get("description"));
        PrismPermissionDefault def = PrismPermissionDefault.fromRaw(map.get("default"));

        Map<String, PrismPermissionNode> children = new LinkedHashMap<>();
        Object childrenNode = map.get("children");
        if (childrenNode instanceof Map<?, ?> childMap) {
            for (Map.Entry<?, ?> entry : childMap.entrySet()) {
                String childName = String.valueOf(entry.getKey());
                children.put(childName, toPermissionNode(childName, asMap(entry.getValue())));
            }
        }

        return new PrismPermissionNode(name, description, def, children);
    }

    private static String stripComment(String line) {
        boolean inSingle = false;
        boolean inDouble = false;
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '\'' && !inDouble) inSingle = !inSingle;
            if (c == '"' && !inSingle) inDouble = !inDouble;
            if (c == '#' && !inSingle && !inDouble) break;
            out.append(c);
        }
        return out.toString().replace('\t', ' ').stripTrailing();
    }

    private static int indentOf(String line) {
        int count = 0;
        while (count < line.length() && line.charAt(count) == ' ') {
            count++;
        }
        return count;
    }

    private static final class Parser {
        private final List<Line> lines;
        private int index = 0;

        private Parser(List<Line> lines) {
            this.lines = lines;
        }

        private Map<String, Object> parseMap(int indent) {
            Map<String, Object> map = new LinkedHashMap<>();
            while (index < lines.size()) {
                Line line = lines.get(index);
                if (line.indent() < indent) {
                    break;
                }
                if (line.indent() > indent) {
                    index++;
                    continue;
                }

                if (line.text().startsWith("- ")) {
                    throw new IllegalArgumentException("Unexpected list item at top-level map: " + line.text());
                }

                int colon = line.text().indexOf(':');
                if (colon < 0) {
                    throw new IllegalArgumentException("Invalid YAML line: " + line.text());
                }

                String key = line.text().substring(0, colon).trim().toLowerCase();
                String rest = line.text().substring(colon + 1).trim();
                index++;

                if (rest.equals(">") || rest.equals("|")) {
                    map.put(key, parseFoldedScalar(line.indent()));
                    continue;
                }

                if (rest.isEmpty()) {
                    if (index < lines.size() && lines.get(index).indent() > indent) {
                        if (lines.get(index).text().startsWith("- ")) {
                            map.put(key, parseList(lines.get(index).indent()));
                        } else {
                            map.put(key, parseMap(lines.get(index).indent()));
                        }
                    } else {
                        map.put(key, new LinkedHashMap<String, Object>());
                    }
                    continue;
                }

                map.put(key, parseScalar(rest));
            }
            return map;
        }

        private List<Object> parseList(int indent) {
            List<Object> list = new ArrayList<>();
            while (index < lines.size()) {
                Line line = lines.get(index);
                if (line.indent() < indent) {
                    break;
                }
                if (line.indent() > indent) {
                    index++;
                    continue;
                }
                if (!line.text().startsWith("- ")) {
                    break;
                }
                String item = line.text().substring(2).trim();
                index++;
                if (item.isEmpty()) {
                    if (index < lines.size() && lines.get(index).indent() > indent) {
                        if (lines.get(index).text().startsWith("- ")) {
                            list.add(parseList(lines.get(index).indent()));
                        } else {
                            list.add(parseMap(lines.get(index).indent()));
                        }
                    } else {
                        list.add("");
                    }
                } else {
                    list.add(parseScalar(item));
                }
            }
            return list;
        }

        private String parseFoldedScalar(int parentIndent) {
            StringBuilder out = new StringBuilder();
            while (index < lines.size()) {
                Line next = lines.get(index);
                if (next.indent() <= parentIndent) {
                    break;
                }
                String text = next.text().trim();
                if (!text.isEmpty()) {
                    if (!out.isEmpty()) {
                        out.append(' ');
                    }
                    out.append(text);
                }
                index++;
            }
            return out.toString();
        }
    }

    private static Object parseScalar(String value) {
        String v = unquote(value);

        if ("true".equalsIgnoreCase(v)) return Boolean.TRUE;
        if ("false".equalsIgnoreCase(v)) return Boolean.FALSE;

        if (v.matches("^-?\\d+$")) {
            try {
                return Integer.parseInt(v);
            } catch (NumberFormatException ignored) {
            }
        }

        if (v.matches("^-?\\d+\\.\\d+$")) {
            try {
                return Double.parseDouble(v);
            } catch (NumberFormatException ignored) {
            }
        }

        if (v.startsWith("[") && v.endsWith("]")) {
            String inside = v.substring(1, v.length() - 1).trim();
            if (inside.isEmpty()) {
                return List.of();
            }
            String[] parts = inside.split(",");
            List<String> out = new ArrayList<>(parts.length);
            for (String part : parts) {
                out.add(unquote(part.trim()));
            }
            return out;
        }

        if (v.equals("{}")) {
            return new LinkedHashMap<String, Object>();
        }

        return v;
    }

    private static String unquote(String value) {
        if ((value.startsWith("\"") && value.endsWith("\"")) || (value.startsWith("'") && value.endsWith("'"))) {
            return value.substring(1, value.length() - 1);
        }
        return value;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> asMap(Object value) {
        if (value instanceof Map<?, ?> map) {
            return (Map<String, Object>) map;
        }
        return new LinkedHashMap<>();
    }

    private static String asString(Object value) {
        return value == null ? "" : String.valueOf(value);
    }

    @SuppressWarnings("unchecked")
    private static List<String> asStringList(Object value) {
        if (value == null) {
            return List.of();
        }
        if (value instanceof List<?> list) {
            List<String> out = new ArrayList<>(list.size());
            for (Object item : list) {
                out.add(String.valueOf(item));
            }
            return List.copyOf(out);
        }
        if (value instanceof String s) {
            if (s.isBlank()) {
                return List.of();
            }
            return List.of(s);
        }
        return List.of();
    }

    private record Line(int indent, String text) {}
}
