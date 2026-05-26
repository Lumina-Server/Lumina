package dev.lumina.flux.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public final class CompressionCodec {
    private final int level;

    public CompressionCodec(int level) {
        this.level = Math.max(0, Math.min(9, level));
    }

    public int level() {
        return level;
    }

    public byte[] compress(byte[] input) {
        if (input == null || input.length == 0) {
            return new byte[0];
        }

        Deflater deflater = new Deflater(level);
        deflater.setInput(input);
        deflater.finish();

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            while (!deflater.finished()) {
                int count = deflater.deflate(buffer);
                out.write(buffer, 0, count);
            }
            deflater.end();
            return out.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException("Compression failed", e);
        }
    }

    public byte[] decompress(byte[] input) {
        if (input == null || input.length == 0) {
            return new byte[0];
        }

        Inflater inflater = new Inflater();
        inflater.setInput(input);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                if (count == 0 && inflater.needsInput()) {
                    break;
                }
                out.write(buffer, 0, count);
            }
            inflater.end();
            return out.toByteArray();
        } catch (Exception e) {
            throw new IllegalStateException("Decompression failed", e);
        }
    }
}
