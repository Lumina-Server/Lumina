package dev.lumina.prism;

import dev.lumina.api.LuminaPlugin;
import dev.lumina.api.PluginContext;

import java.net.URLClassLoader;
import java.util.Objects;

public final class PrismPluginHandle {
    private final PrismPluginDescriptor descriptor;
    private final PrismPluginClassLoader classLoader;
    private final LuminaPlugin plugin;
    private final PluginContext context;

    public PrismPluginHandle(
            PrismPluginDescriptor descriptor,
            PrismPluginClassLoader classLoader,
            LuminaPlugin plugin,
            PluginContext context
    ) {
        this.descriptor = Objects.requireNonNull(descriptor, "descriptor");
        this.classLoader = Objects.requireNonNull(classLoader, "classLoader");
        this.plugin = Objects.requireNonNull(plugin, "plugin");
        this.context = Objects.requireNonNull(context, "context");
    }

    public PrismPluginDescriptor descriptor() {
        return descriptor;
    }

    public LuminaPlugin plugin() {
        return plugin;
    }

    public void load() {
        plugin.onLoad(context);
    }

    public void enable() {
        plugin.onEnable(context);
    }

    public void disable() {
        plugin.onDisable(context);
    }

    public void close() {
        try {
            classLoader.close();
        } catch (Exception ignored) {
        }
    }
}
