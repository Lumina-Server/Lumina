// lumina-plugin/src/main/java/dev/lumina/plugin/PluginContainer.java
package dev.lumina.plugin;

import java.net.URLClassLoader;

public final class PluginContainer {
    private final URLClassLoader classLoader;
    private final LoadedPlugin plugin;

    public PluginContainer(URLClassLoader classLoader, LoadedPlugin plugin) {
        this.classLoader = classLoader;
        this.plugin = plugin;
    }

    public URLClassLoader classLoader() {
        return classLoader;
    }

    public LoadedPlugin plugin() {
        return plugin;
    }
}
