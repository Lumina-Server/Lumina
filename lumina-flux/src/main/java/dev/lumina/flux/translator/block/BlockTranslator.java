package dev.lumina.flux.translator.block;

import dev.lumina.flux.util.RuntimeIdRegistry;

public final class BlockTranslator {
    private final RuntimeIdRegistry registry = new RuntimeIdRegistry();

    public BlockTranslator() {
        registry.register("minecraft:stone", 1);
        registry.register("minecraft:dirt", 2);
        registry.register("minecraft:grass_block", 3);
    }

    public String toBedrockBlock(String javaBlockId) {
        return registry.idOf(javaBlockId).map(id -> "bedrock:" + id).orElse(javaBlockId);
    }

    public String toJavaBlock(String bedrockBlockId) {
        return bedrockBlockId == null ? "" : bedrockBlockId.replace("bedrock:", "minecraft:");
    }

    public RuntimeIdRegistry registry() {
        return registry;
    }
}
