package dev.lumina.flux.chunk;

import dev.lumina.flux.util.BedrockDataPalette;

public final class BlockPaletteTranslator {
    private final BedrockDataPalette palette = new BedrockDataPalette();

    public BlockPaletteTranslator() {
        palette.register("minecraft:stone", 1);
        palette.register("minecraft:dirt", 2);
    }

    public int toRuntimeId(String javaBlockId) {
        return palette.runtimeIdOf(javaBlockId).orElse(0);
    }

    public String toJavaBlock(int runtimeId) {
        return palette.idOf(runtimeId).orElse("minecraft:air");
    }

    public BedrockDataPalette palette() {
        return palette;
    }
}
