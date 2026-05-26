package dev.lumina.prism;

import dev.lumina.api.LuminaPlugin;
import dev.lumina.api.PluginContext;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class PrismPluginLoader {
    private final PluginYamlParser parser = new PluginYamlParser();

    public List<PrismPluginHandle> loadAll(Path pluginsDirectory, PluginContext context) throws IOException {
        if (!Files.exists(pluginsDirectory)) {
            Files.createDirectories(pluginsDirectory);
        }

        List<PrismPluginHandle> loaded = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(pluginsDirectory, "*.jar")) {
            for (Path jar : stream) {
                PrismPluginHandle handle = loadOne(jar, context);
                if (handle != null) {
                    loaded.add(handle);
                }
            }
        }
        return List.copyOf(loaded);
    }

    public PrismPluginHandle loadOne(Path jar, PluginContext context) {
        try {
            URL[] urls = { jar.toUri().toURL() };
            PrismPluginClassLoader loader = new PrismPluginClassLoader(urls, getClass().getClassLoader());

            PrismPluginDescriptor descriptor;
            try (InputStream in = loader.getResourceAsStream("plugin.yml")) {
                if (in == null) {
                    return null;
                }
                descriptor = parser.parse(in);
            }

            Class<?> mainClass = Class.forName(descriptor.mainClass(), true, loader);
            Object instance = mainClass.getDeclaredConstructor().newInstance();
            if (!(instance instanceof LuminaPlugin plugin)) {
                return null;
            }

            return new PrismPluginHandle(descriptor, loader, plugin, context);
        } catch (Throwable t) {
            context.logger().error("Failed to load Prism plugin: " + jar, t);
            return null;
        }
    }
}
