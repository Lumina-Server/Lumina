package dev.lumina.server.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class RegistryCache {
    private final Map<String, Integer> entries = new ConcurrentHashMap<>();

    public void put(String key, int value) {
        entries.put(key, value);
    }

    public Integer get(String key) {
        return entries.get(key);
    }

    public Map<String, Integer> all() {
        return Map.copyOf(entries);
    }
}
