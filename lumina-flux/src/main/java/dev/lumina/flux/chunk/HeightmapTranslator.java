package dev.lumina.flux.chunk;

public final class HeightmapTranslator {
    public int[] toBedrockHeightmap(int[] javaHeightmap) {
        if (javaHeightmap == null) {
            return new int[0];
        }
        return javaHeightmap.clone();
    }

    public int[] toJavaHeightmap(int[] bedrockHeightmap) {
        if (bedrockHeightmap == null) {
            return new int[0];
        }
        return bedrockHeightmap.clone();
    }
}
