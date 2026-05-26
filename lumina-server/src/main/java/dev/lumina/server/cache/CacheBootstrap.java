package dev.lumina.server.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class CacheBootstrap {
    public void bootstrap(Path cacheDir) throws IOException {
        Files.createDirectories(cacheDir);
    }
}
