package dev.lumina.server;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

public final class RuntimeConfigLoader {

    public RuntimeConfig load(Path root) throws IOException {
        Objects.requireNonNull(root, "root");
        Path file = root.resolve("server.properties");

        if (!Files.exists(file)) {
            new ServerPropertiesGenerator().generate(root);
        }

        Properties properties = new Properties();
        try (InputStream in = Files.newInputStream(file)) {
            properties.load(in);
        }

        RuntimeDirectories dirs = RuntimeDirectories.of(root);

        return new RuntimeConfig(
                prop(properties, "server-name", "Lumina"),
                prop(properties, "motd", "Powered by Lumina"),
                prop(properties, "level-name", "world"),
                prop(properties, "server-ip", ""),
                intProp(properties, "server-port", 25565),
                intProp(properties, "bedrock-port", 19132),
                intProp(properties, "max-players", 100),
                boolProp(properties, "online-mode", true),
                boolProp(properties, "enable-prism", true),
                boolProp(properties, "enable-bedrock", true),
                boolProp(properties, "enable-zerotier", false),
                boolProp(properties, "debug", false),
                boolProp(properties, "log-ips", true),
                boolProp(properties, "enable-async-chunks", true),
                boolProp(properties, "enable-regionized-ticking", true),
                boolProp(properties, "enable-parallel-worlds", true),
                intProp(properties, "lumina-worker-threads", 4),
                intProp(properties, "lumina-io-threads", 2),
                intProp(properties, "lumina-network-threads", 2),
                intProp(properties, "lumina-tick-rate", 20),
                dirs.plugins(),
                dirs.worlds(),
                dirs.logs(),
                dirs.cache()
        );
    }

    private static String prop(Properties properties, String key, String def) {
        String value = properties.getProperty(key);
        return value == null || value.isBlank() ? def : value.trim();
    }

    private static int intProp(Properties properties, String key, int def) {
        String value = properties.getProperty(key);
        if (value == null || value.isBlank()) return def;
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException ignored) {
            return def;
        }
    }

    private static boolean boolProp(Properties properties, String key, boolean def) {
        String value = properties.getProperty(key);
        if (value == null || value.isBlank()) return def;
        return Boolean.parseBoolean(value.trim());
    }
}
