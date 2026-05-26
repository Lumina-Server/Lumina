package dev.lumina.flux.util;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class BedrockDataPalette {
    private final Map<String, Integer> ids = new ConcurrentHashMap<>();
    private final Map<Integer, String> reverse = new ConcurrentHashMap<>();

    public void register(String id, int runtimeId) {
        ids.put(id, runtimeId);
        reverse.put(runtimeId, id);
    }

    public Optional<Integer> runtimeIdOf(String id) {
        return Optional.ofNullable(ids.get(id));
    }

    public Optional<String> idOf(int runtimeId) {
        return Optional.ofNullable(reverse.get(runtimeId));
    }
}
