package dev.lumina.flux.translator.biome;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class BiomeTranslator {
    private final Map<String, String> map = new ConcurrentHashMap<>();

    public BiomeTranslator() {
        map.put("minecraft:plains", "bedrock:plains");
        map.put("minecraft:desert", "bedrock:desert");
    }

    public String toBedrockBiome(String javaBiomeId) {
        return map.getOrDefault(javaBiomeId, javaBiomeId);
    }

    public String toJavaBiome(String bedrockBiomeId) {
        return map.entrySet().stream()
                .filter(e -> e.getValue().equals(bedrockBiomeId))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(bedrockBiomeId);
    }
}
