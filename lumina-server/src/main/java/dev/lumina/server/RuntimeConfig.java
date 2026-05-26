package dev.lumina.server;

import java.nio.file.Path;
import java.util.Objects;

public record RuntimeConfig(
        String serverName,
        String motd,
        String levelName,
        String serverIp,
        int serverPort,
        int bedrockPort,
        int maxPlayers,
        boolean onlineMode,
        boolean enablePrism,
        boolean enableBedrock,
        boolean enableZeroTier,
        boolean debug,
        boolean logIps,
        boolean asyncChunks,
        boolean regionizedTicking,
        boolean parallelWorlds,
        int workerThreads,
        int ioThreads,
        int networkThreads,
        int tickRate,
        Path pluginsDir,
        Path worldsDir,
        Path logsDir,
        Path cacheDir
) {
    public RuntimeConfig {
        serverName = requireText(serverName, "serverName");
        motd = requireText(motd, "motd");
        levelName = requireText(levelName, "levelName");
        serverIp = serverIp == null ? "" : serverIp.trim();
        pluginsDir = Objects.requireNonNull(pluginsDir, "pluginsDir");
        worldsDir = Objects.requireNonNull(worldsDir, "worldsDir");
        logsDir = Objects.requireNonNull(logsDir, "logsDir");
        cacheDir = Objects.requireNonNull(cacheDir, "cacheDir");
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value.trim();
    }
}
