package dev.lumina.flux.chunk;

public final class ChunkSectionTranslator {
    private final BlockPaletteTranslator paletteTranslator = new BlockPaletteTranslator();

    public int[] translate(String[] sectionBlocks) {
        if (sectionBlocks == null) {
            return new int[0];
        }

        int[] out = new int[sectionBlocks.length];
        for (int i = 0; i < sectionBlocks.length; i++) {
            out[i] = paletteTranslator.toRuntimeId(sectionBlocks[i]);
        }
        return out;
    }
}
