// lumina-plugin/src/main/java/dev/lumina/plugin/PluginManager.java
package dev.lumina.plugin;

import dev.lumina.api.LuminaPlugin;
import dev.lumina.api.PluginDescription;
import dev.lumina.api.PluginContext;
import dev.lumina.logging.LuminaLogger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;
import java.util.*;

public final class PluginManager {
    private final List<PluginContainer> loaded = new ArrayList<>();

    public List<PluginContainer> loaded() {
        return List.copyOf(loaded);
    }

    public void loadPlugins(Path directory, PluginContext context) throws IOException {
        if (!Files.exists(directory)) Files.createDirectories(directory);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory, "*.jar")) {
            for (Path jar : stream) {
                loadJar(jar, context);
            }
        }
    }

    private void loadJar(Path jar, PluginContext context) {
        try {
            URL[] urls = { jar.toUri().toURL() };
            PluginClassLoader loader = new PluginClassLoader(urls, getClass().getClassLoader());
            Properties meta = new Properties();
            try (InputStream in = loader.getResourceAsStream("lumina-plugin.properties")) {
                if (in == null) return;
                meta.load(in);
            }
            String name = meta.getProperty("name", jar.getFileName().toString());
            String version = meta.getProperty("version", "0.0.0");
            String mainClass = meta.getProperty("main");
            if (mainClass == null || mainClass.isBlank()) return;

            Class<?> clazz = Class.forName(mainClass, true, loader);
            Object obj = clazz.getDeclaredConstructor().newInstance();
            if (!(obj instanceof LuminaPlugin plugin)) return;

            PluginDescription desc = new PluginDescription(name, version, mainClass);
            LoadedPlugin loadedPlugin = new LoadedPlugin(desc, plugin);
            loaded.add(new PluginContainer(loader, loadedPlugin));
            plugin.onLoad(context);
            plugin.onEnable(context);
        } catch (Throwable t) {
            LuminaLogger logger = context.logger();
            logger.error("Failed to load plugin from " + jar, t);
        }
    }
}
