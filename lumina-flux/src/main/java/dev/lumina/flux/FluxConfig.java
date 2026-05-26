package dev.lumina.flux;

import java.util.Objects;

public record FluxConfig(
        String host,
        int port,
        String motd,
        String subMotd,
        int compressionLevel,
        int maxPlayers,
        int rakNetThreads,
        boolean packetBatching,
        boolean floodgateAuth,
        boolean debugLogging
) {
    public FluxConfig {
        host = requireText(host, "host");
        motd = requireText(motd, "motd");
        subMotd = subMotd == null ? "" : subMotd.trim();
        if (compressionLevel < 0 || compressionLevel > 9) {
            throw new IllegalArgumentException("compressionLevel must be between 0 and 9");
        }
        if (maxPlayers < 0) {
            throw new IllegalArgumentException("maxPlayers must be >= 0");
        }
        if (rakNetThreads < 1) {
            throw new IllegalArgumentException("rakNetThreads must be >= 1");
        }
    }

    private static String requireText(String value, String field) {
        Objects.requireNonNull(value, field);
        if (value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value.trim();
    }
}
