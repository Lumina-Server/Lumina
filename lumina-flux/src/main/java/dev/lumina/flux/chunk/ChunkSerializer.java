package dev.lumina.flux.chunk;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public final class ChunkSerializer {
    private final ChunkSectionTranslator sectionTranslator = new ChunkSectionTranslator();
    private final HeightmapTranslator heightmapTranslator = new HeightmapTranslator();

    public byte[] serialize(String[] blocks, int[] heightmap) {
        int[] section = sectionTranslator.translate(blocks);
        int[] hm = heightmapTranslator.toBedrockHeightmap(heightmap);
        String payload = "section=" + Arrays.toString(section) + ";heightmap=" + Arrays.toString(hm);
        return payload.getBytes(StandardCharsets.UTF_8);
    }

    public ChunkSectionTranslator sectionTranslator() {
        return sectionTranslator;
    }

    public HeightmapTranslator heightmapTranslator() {
        return heightmapTranslator;
    }
}
