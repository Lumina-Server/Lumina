// lumina-common/src/main/java/dev/lumina/common/Identifier.java
package dev.lumina.common;

import java.util.Objects;
import java.util.regex.Pattern;

public record Identifier(String namespace, String value) {
    private static final Pattern VALID = Pattern.compile("^[a-z0-9._-]+$");

    public Identifier {
        namespace = normalize(namespace, "minecraft");
        value = normalize(value, null);
        validate(namespace, "namespace");
        validate(value, "value");
    }

    public static Identifier parse(String raw) {
        Objects.requireNonNull(raw, "raw");
        int idx = raw.indexOf(':');
        if (idx < 0) {
            return new Identifier("minecraft", raw);
        }
        return new Identifier(raw.substring(0, idx), raw.substring(idx + 1));
    }

    private static String normalize(String input, String fallback) {
        String out = input == null || input.isBlank() ? fallback : input.trim().toLowerCase();
        if (out == null) {
            throw new IllegalArgumentException("Identifier part cannot be null or blank");
        }
        return out;
    }

    private static void validate(String text, String part) {
        if (!VALID.matcher(text).matches()) {
            throw new IllegalArgumentException("Invalid " + part + ": " + text);
        }
    }

    @Override
    public String toString() {
        return namespace + ":" + value;
    }
}
