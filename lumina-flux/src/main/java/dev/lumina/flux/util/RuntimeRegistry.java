package dev.lumina.flux.util;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class RuntimeIdRegistry {
    private final Map<String, Integer> ids = new ConcurrentHashMap<>();
    private final Map<Integer, String> reverse = new ConcurrentHashMap<>();

    public void register(String javaId, int runtimeId) {
        ids.put(javaId, runtimeId);
        reverse.put(runtimeId, javaId);
    }

    public Optional<Integer> idOf(String javaId) {
        return Optional.ofNullable(ids.get(javaId));
    }

    public Optional<String> javaIdOf(int runtimeId) {
        return Optional.ofNullable(reverse.get(runtimeId));
    }
}
