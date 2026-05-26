package dev.lumina.flux.translator.entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class BedrockEntityMapper {
    private final EntityIdMap idMap = new EntityIdMap();

    public BedrockEntityMapper() {
        idMap.register("minecraft:player", 1);
        idMap.register("minecraft:zombie", 2);
        idMap.register("minecraft:cow", 3);
    }

    public int toBedrockEntityId(String javaEntityId) {
        return idMap.bedrockIdOf(javaEntityId).orElse(0);
    }

    public String toJavaEntityId(int bedrockEntityId) {
        return idMap.javaIdOf(bedrockEntityId).orElse("minecraft:unknown");
    }

    public EntityIdMap idMap() {
        return idMap;
    }
}
