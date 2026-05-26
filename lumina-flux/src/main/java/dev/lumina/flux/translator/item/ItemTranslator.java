package dev.lumina.flux.translator.item;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ItemTranslator {
    private final Map<String, String> map = new ConcurrentHashMap<>();

    public ItemTranslator() {
        map.put("minecraft:diamond_sword", "bedrock:diamond_sword");
        map.put("minecraft:stone", "bedrock:stone");
    }

    public String toBedrockItem(String javaItemId) {
        return map.getOrDefault(javaItemId, javaItemId);
    }

    public String toJavaItem(String bedrockItemId) {
        return map.entrySet().stream()
                .filter(e -> e.getValue().equals(bedrockItemId))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(bedrockItemId);
    }
}
