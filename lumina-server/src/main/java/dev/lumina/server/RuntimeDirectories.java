package dev.lumina.server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public record RuntimeDirectories(
        Path root,
        Path plugins,
        Path worlds,
        Path logs,
        Path cache,
        Path libraries
) {
    public RuntimeDirectories {
        root = Objects.requireNonNull(root, "root");
        plugins = Objects.requireNonNull(plugins, "plugins");
        worlds = Objects.requireNonNull(worlds, "worlds");
        logs = Objects.requireNonNull(logs, "logs");
        cache = Objects.requireNonNull(cache, "cache");
        libraries = Objects.requireNonNull(libraries, "libraries");
    }

    public static RuntimeDirectories of(Path root) {
        Objects.requireNonNull(root, "root");
        return new RuntimeDirectories(
                root,
                root.resolve("plugins"),
                root.resolve("worlds"),
                root.resolve("logs"),
                root.resolve("cache"),
                root.resolve("libraries")
        );
    }

    public void ensure() throws IOException {
        Files.createDirectories(root);
        Files.createDirectories(plugins);
        Files.createDirectories(worlds);
        Files.createDirectories(logs);
        Files.createDirectories(cache);
        Files.createDirectories(libraries);
    }
}
