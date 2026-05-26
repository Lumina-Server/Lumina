// lumina-launcher/src/main/java/dev/lumina/launcher/LaunchArguments.java
package dev.lumina.launcher;

import java.nio.file.Path;

public record LaunchArguments(Path configPath, Path pluginsDir) {}
