package dev.lumina.server;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public final class ResourceConfigExtractor {

    public void extract(String resourcePath, Path root) throws IOException {
        Objects.requireNonNull(resourcePath, "resourcePath");
        Objects.requireNonNull(root, "root");

        Path target = root.resolve(resourcePath);
        if (Files.exists(target)) {
            return;
        }

        Files.createDirectories(target.getParent());

        try (InputStream in = getClass().getClassLoader().getResourceAsStream("configurations/" + resourcePath)) {
            if (in == null) {
                throw new IllegalStateException("Missing internal resource: configurations/" + resourcePath);
            }
            Files.copy(in, target);
        }
    }

    public void extractJson(String resourceName, Path root) throws IOException {
        Objects.requireNonNull(resourceName, "resourceName");
        Objects.requireNonNull(root, "root");

        Path target = root.resolve(resourceName);
        if (Files.exists(target)) {
            return;
        }

        Files.createDirectories(target.getParent());

        try (InputStream in = getClass().getClassLoader().getResourceAsStream("json/" + resourceName)) {
            if (in == null) {
                throw new IllegalStateException("Missing internal resource: json/" + resourceName);
            }
            Files.copy(in, target);
        }
    }
}
