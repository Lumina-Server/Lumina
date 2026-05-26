package dev.lumina.prism;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public record PrismPermissionNode(
        String name,
        String description,
        PrismPermissionDefault defaultValue,
        Map<String, PrismPermissionNode> children
) {
    public PrismPermissionNode {
        name = requireText(name, "name");
        description = description == null ? "" : description.trim();
        defaultValue = defaultValue == null ? PrismPermissionDefault.OP : defaultValue;
        children = children == null ? Map.of() : Collections.unmodifiableMap(children);
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value.trim();
    }

    public PrismPermissionNode child(String childName) {
        Objects.requireNonNull(childName, "childName");
        return children.get(childName);
    }
}
