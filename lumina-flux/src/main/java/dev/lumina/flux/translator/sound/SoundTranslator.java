package dev.lumina.flux.translator.sound;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class SoundTranslator {
    private final Map<String, String> map = new ConcurrentHashMap<>();

    public SoundTranslator() {
        map.put("minecraft:block.anvil.land", "bedrock:block.anvil.land");
    }

    public String toBedrockSound(String javaSoundId) {
        return map.getOrDefault(javaSoundId, javaSoundId);
    }

    public String toJavaSound(String bedrockSoundId) {
        return map.entrySet().stream()
                .filter(e -> e.getValue().equals(bedrockSoundId))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(bedrockSoundId);
    }
}
