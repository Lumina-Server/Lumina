package dev.lumina.flux.translator.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public final class EntityMetadataTranslator {

    public Map<String, Object> toBedrockMetadata(Map<String, Object> javaMetadata) {
        return copy(javaMetadata);
    }

    public Map<String, Object> toJavaMetadata(Map<String, Object> bedrockMetadata) {
        return copy(bedrockMetadata);
    }

    private static Map<String, Object> copy(Map<String, Object> input) {
        Map<String, Object> out = new LinkedHashMap<>();
        if (input != null) {
            out.putAll(input);
        }
        return out;
    }
}
