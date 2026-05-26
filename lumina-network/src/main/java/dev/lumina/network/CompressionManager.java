// lumina-network/src/main/java/dev/lumina/network/CompressionManager.java
package dev.lumina.network;

public final class CompressionManager {
    private final int threshold;

    public CompressionManager(int threshold) {
        this.threshold = threshold;
    }

    public int threshold() {
        return threshold;
    }

    public boolean shouldCompress(int size) {
        return threshold >= 0 && size >= threshold;
    }
}
