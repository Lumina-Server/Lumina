package dev.lumina.prism;

import dev.lumina.api.PluginContext;
import dev.lumina.command.CommandManager;
import dev.lumina.event.EventBus;
import dev.lumina.logging.LuminaLogger;
import dev.lumina.scheduler.Scheduler;
import dev.lumina.world.WorldManager;

import java.util.Objects;

public final class PrismPluginContextImpl implements PluginContext {
    private final LuminaLogger logger;
    private final Scheduler scheduler;
    private final EventBus eventBus;
    private final WorldManager worlds;
    private final CommandManager commands;

    public PrismPluginContextImpl(
            LuminaLogger logger,
            Scheduler scheduler,
            EventBus eventBus,
            WorldManager worlds,
            CommandManager commands
    ) {
        this.logger = Objects.requireNonNull(logger, "logger");
        this.scheduler = Objects.requireNonNull(scheduler, "scheduler");
        this.eventBus = Objects.requireNonNull(eventBus, "eventBus");
        this.worlds = Objects.requireNonNull(worlds, "worlds");
        this.commands = Objects.requireNonNull(commands, "commands");
    }

    @Override
    public LuminaLogger logger() {
        return logger;
    }

    @Override
    public Scheduler scheduler() {
        return scheduler;
    }

    @Override
    public EventBus eventBus() {
        return eventBus;
    }

    @Override
    public WorldManager worlds() {
        return worlds;
    }

    public CommandManager commands() {
        return commands;
    }
}
