package dev.lumina.server.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class JsonConfigLoader {
    public String load(Path file) throws IOException {
        return Files.readString(file, StandardCharsets.UTF_8);
    }
}
