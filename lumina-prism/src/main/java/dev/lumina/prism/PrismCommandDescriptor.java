package dev.lumina.prism;

import java.util.List;

public record PrismCommandDescriptor(
        String name,
        String description,
        String usage,
        List<String> aliases
) {
    public PrismCommandDescriptor {
        name = requireText(name, "name");
        description = description == null ? "" : description.trim();
        usage = usage == null ? "" : usage.trim();
        aliases = aliases == null ? List.of() : List.copyOf(aliases);
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value.trim();
    }
}
