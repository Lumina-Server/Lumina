package dev.lumina.flux.translator.entity;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class EntityIdMap {
    private final Map<String, Integer> javaToBedrock = new ConcurrentHashMap<>();
    private final Map<Integer, String> bedrockToJava = new ConcurrentHashMap<>();

    public void register(String javaId, int bedrockId) {
        javaToBedrock.put(javaId, bedrockId);
        bedrockToJava.put(bedrockId, javaId);
    }

    public Optional<Integer> bedrockIdOf(String javaId) {
        return Optional.ofNullable(javaToBedrock.get(javaId));
    }

    public Optional<String> javaIdOf(int bedrockId) {
        return Optional.ofNullable(bedrockToJava.get(bedrockId));
    }
}
