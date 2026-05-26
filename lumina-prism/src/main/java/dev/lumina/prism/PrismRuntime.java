package dev.lumina.prism;

import dev.lumina.api.PluginContext;
import dev.lumina.command.CommandManager;
import dev.lumina.event.EventBus;
import dev.lumina.logging.LuminaLogger;
import dev.lumina.scheduler.Scheduler;
import dev.lumina.world.WorldManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class PrismRuntime {
    private final PrismPluginLoader loader = new PrismPluginLoader();
    private final PrismPermissionRegistry permissions = new PrismPermissionRegistry();
    private final List<PrismPluginHandle> handles = new ArrayList<>();
    private final PrismPluginContextImpl context;

    public PrismRuntime(
            LuminaLogger logger,
            Scheduler scheduler,
            EventBus eventBus,
            WorldManager worlds,
            CommandManager commands
    ) {
        this.context = new PrismPluginContextImpl(logger, scheduler, eventBus, worlds, commands);
    }

    public PluginContext context() {
        return context;
    }

    public PrismPermissionRegistry permissions() {
        return permissions;
    }

    public List<PrismPluginHandle> handles() {
        return List.copyOf(handles);
    }

    public void load(Path pluginsDirectory) throws IOException {
        unload();
        handles.addAll(loader.loadAll(pluginsDirectory, context));
        for (PrismPluginHandle handle : handles) {
            handle.descriptor().permissions().values().forEach(permissions::register);
        }
    }

    public void enableAll() {
        for (PrismPluginHandle handle : handles) {
            handle.load();
        }
        for (PrismPluginHandle handle : handles) {
            handle.enable();
        }
    }

    public void unload() {
        for (int i = handles.size() - 1; i >= 0; i--) {
            PrismPluginHandle handle = handles.get(i);
            try {
                handle.disable();
            } finally {
                handle.close();
            }
        }
        handles.clear();
    }
}
