package dev.lumina.server.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class PluginCache {
    private final Map<String, String> loaded = new ConcurrentHashMap<>();

    public void put(String name, String version) {
        loaded.put(name, version);
    }

    public Map<String, String> all() {
        return Map.copyOf(loaded);
    }
}
