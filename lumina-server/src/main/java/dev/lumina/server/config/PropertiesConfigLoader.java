package dev.lumina.server.config;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public final class PropertiesConfigLoader {
    public Properties load(Path file) throws IOException {
        Properties properties = new Properties();
        try (var in = java.nio.file.Files.newInputStream(file)) {
            properties.load(in);
        }
        return properties;
    }
}
