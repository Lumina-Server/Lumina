// lumina-config/src/main/java/dev/lumina/config/ConfigLoader.java
package dev.lumina.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public final class ConfigLoader {
    public ConfigNode load(Path path) throws IOException {
        try (Reader reader = Files.newBufferedReader(path)) {
            return load(reader);
        }
    }

    public ConfigNode load(InputStream in) throws IOException {
        return load(new java.io.InputStreamReader(in));
    }

    public ConfigNode load(Reader reader) throws IOException {
        Properties props = new Properties();
        props.load(reader);
        Map<String, Object> root = new LinkedHashMap<>();
        for (String name : props.stringPropertyNames()) {
            putPath(root, name, props.getProperty(name));
        }
        return new MapConfigNode(root);
    }

    @SuppressWarnings("unchecked")
    private static void putPath(Map<String, Object> root, String path, String value) {
        String[] parts = path.split("\\.");
        Map<String, Object> current = root;
        for (int i = 0; i < parts.length - 1; i++) {
            current = (Map<String, Object>) current.computeIfAbsent(parts[i], k -> new LinkedHashMap<>());
        }
        current.put(parts[parts.length - 1], value);
    }

    private static final class MapConfigNode implements ConfigNode {
        private final Map<String, Object> data;

        private MapConfigNode(Map<String, Object> data) {
            this.data = data;
        }

        @Override
        public Optional<String> getString(String key) {
            Object v = data.get(key);
            return v instanceof String s ? Optional.of(s) : Optional.empty();
        }

        @Override
        public Optional<Integer> getInt(String key) {
            Object v = data.get(key);
            if (v instanceof Number n) return Optional.of(n.intValue());
            if (v instanceof String s) {
                try { return Optional.of(Integer.parseInt(s)); } catch (NumberFormatException ignored) {}
            }
            return Optional.empty();
        }

        @Override
        public Optional<Boolean> getBoolean(String key) {
            Object v = data.get(key);
            if (v instanceof Boolean b) return Optional.of(b);
            if (v instanceof String s) return Optional.of(Boolean.parseBoolean(s));
            return Optional.empty();
        }

        @Override
        public Optional<Double> getDouble(String key) {
            Object v = data.get(key);
            if (v instanceof Number n) return Optional.of(n.doubleValue());
            if (v instanceof String s) {
                try { return Optional.of(Double.parseDouble(s)); } catch (NumberFormatException ignored) {}
            }
            return Optional.empty();
        }

        @SuppressWarnings("unchecked")
        @Override
        public ConfigNode getSection(String key) {
            Object v = data.get(key);
            if (v instanceof Map<?, ?> map) {
                return new MapConfigNode((Map<String, Object>) map);
            }
            return new MapConfigNode(Collections.emptyMap());
        }

        @Override
        public List<ConfigNode> getList(String key) {
            Object v = data.get(key);
            if (v instanceof List<?> list) {
                List<ConfigNode> out = new ArrayList<>();
                for (Object o : list) {
                    if (o instanceof Map<?, ?> map) {
                        @SuppressWarnings("unchecked")
                        Map<String, Object> m = (Map<String, Object>) map;
                        out.add(new MapConfigNode(m));
                    }
                }
                return out;
            }
            return List.of();
        }

        @Override
        public Map<String, Object> raw() {
            return Collections.unmodifiableMap(data);
        }
    }
}
