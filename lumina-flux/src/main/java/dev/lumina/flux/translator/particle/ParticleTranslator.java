package dev.lumina.flux.translator.particle;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ParticleTranslator {
    private final Map<String, String> map = new ConcurrentHashMap<>();

    public ParticleTranslator() {
        map.put("minecraft:crit", "bedrock:crit");
        map.put("minecraft:smoke", "bedrock:smoke");
    }

    public String toBedrockParticle(String javaParticleId) {
        return map.getOrDefault(javaParticleId, javaParticleId);
    }

    public String toJavaParticle(String bedrockParticleId) {
        return map.entrySet().stream()
                .filter(e -> e.getValue().equals(bedrockParticleId))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(bedrockParticleId);
    }
}
