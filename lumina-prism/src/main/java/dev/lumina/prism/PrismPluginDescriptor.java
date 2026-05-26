package dev.lumina.prism;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public record PrismPluginDescriptor(
        String name,
        String version,
        String mainClass,
        String website,
        String author,
        String description,
        String apiVersion,
        List<String> depend,
        List<String> softDepend,
        List<String> loadBefore,
        Map<String, PrismCommandDescriptor> commands,
        Map<String, PrismPermissionNode> permissions,
        Map<String, Object> raw
) {
    public PrismPluginDescriptor {
        name = requireText(name, "name");
        version = requireText(version, "version");
        mainClass = requireText(mainClass, "mainClass");

        website = website == null ? "" : website.trim();
        author = author == null ? "" : author.trim();
        description = description == null ? "" : description.trim();
        apiVersion = apiVersion == null ? "" : apiVersion.trim();

        depend = depend == null ? List.of() : List.copyOf(depend);
        softDepend = softDepend == null ? List.of() : List.copyOf(softDepend);
        loadBefore = loadBefore == null ? List.of() : List.copyOf(loadBefore);

        commands = commands == null ? Map.of() : Map.copyOf(commands);
        permissions = permissions == null ? Map.of() : Map.copyOf(permissions);
        raw = raw == null ? Map.of() : Collections.unmodifiableMap(raw);
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value.trim();
    }

    public String normalizedName() {
        return name.toLowerCase();
    }
}
