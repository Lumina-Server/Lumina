package dev.lumina.flux.inventory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ContainerTranslator {
    private final Map<String, String> map = new ConcurrentHashMap<>();

    public ContainerTranslator() {
        map.put("minecraft:chest", "bedrock:chest");
        map.put("minecraft:barrel", "bedrock:barrel");
        map.put("minecraft:hopper", "bedrock:hopper");
    }

    public String toBedrock(String javaContainer) {
        return map.getOrDefault(javaContainer, javaContainer);
    }

    public String toJava(String bedrockContainer) {
        return map.entrySet().stream()
                .filter(e -> e.getValue().equals(bedrockContainer))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(bedrockContainer);
    }
}
