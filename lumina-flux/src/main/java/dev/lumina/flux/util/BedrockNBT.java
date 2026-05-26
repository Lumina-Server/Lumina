package dev.lumina.flux.util;

import java.util.LinkedHashMap;
import java.util.Map;

public final class BedrockNBT {
    private final Map<String, Object> tags = new LinkedHashMap<>();

    public BedrockNBT put(String key, Object value) {
        tags.put(key, value);
        return this;
    }

    public Object get(String key) {
        return tags.get(key);
    }

    public Map<String, Object> asMap() {
        return Map.copyOf(tags);
    }

    @Override
    public String toString() {
        return tags.toString();
    }
}
