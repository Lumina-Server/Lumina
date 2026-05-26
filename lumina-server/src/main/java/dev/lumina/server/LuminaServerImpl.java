package dev.lumina.server;

import dev.lumina.api.LuminaServer;
import dev.lumina.command.CommandManager;
import dev.lumina.entity.EntityManager;
import dev.lumina.event.EventBus;
import dev.lumina.event.EventBusImpl;
import dev.lumina.flux.BedrockGateway;
import dev.lumina.logging.LuminaLogger;
import dev.lumina.prism.PrismRuntime;
import dev.lumina.scheduler.Scheduler;
import dev.lumina.scheduler.SchedulerImpl;
import dev.lumina.scheduler.TaskHandle;
import dev.lumina.server.event.ServerLifecycleEvent;
import dev.lumina.server.event.ServerTickEvent;
import dev.lumina.world.WorldManager;

import java.nio.file.Path;
import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public final class LuminaServerImpl implements LuminaServer {
    private final Path root;
    private final RuntimeConfig config;

    private final LuminaLogger logger;
    private final Scheduler scheduler;
    private final EventBus eventBus;
    private final WorldManager worlds;
    private final EntityManager entities;
    private final CommandManager commands;

    private final PrismRuntime prismRuntime;
    private final BedrockGateway bedrockGateway;

    private final AtomicBoolean started = new AtomicBoolean(false);
    private final AtomicLong tickCounter = new AtomicLong(0L);

    private volatile TaskHandle tickHandle;

    public LuminaServerImpl(Path root, RuntimeConfig config, LuminaLogger logger) {
        this.root = Objects.requireNonNull(root, "root");
        this.config = Objects.requireNonNull(config, "config");
        this.logger = Objects.requireNonNull(logger, "logger");

        this.scheduler = new SchedulerImpl();
        this.eventBus = new EventBusImpl();
        this.worlds = new WorldManager();
        this.entities = new EntityManager();
        this.commands = new CommandManager();

        this.prismRuntime = new PrismRuntime(logger, scheduler, eventBus, worlds, commands);
        this.bedrockGateway = config.enableBedrock() ? new BedrockGateway() : null;
    }

    public Path root() {
        return root;
    }

    public RuntimeConfig config() {
        return config;
    }

    public BedrockGateway bedrockGateway() {
        return bedrockGateway;
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

    @Override
    public EntityManager entities() {
        return entities;
    }

    @Override
    public CommandManager commands() {
        return commands;
    }

    @Override
    public synchronized void start() {
        if (!started.compareAndSet(false, true)) {
            return;
        }

        logger.info("Starting Lumina...");
        eventBus.post(new ServerLifecycleEvent(ServerLifecycleEvent.Phase.STARTING, Instant.now()));

        worlds.create(config.levelName());

        if (config.enablePrism()) {
            try {
                prismRuntime.load(root.resolve("plugins"));
                prismRuntime.enableAll();
                logger.info("Prism compatibility layer enabled.");
            } catch (Exception e) {
                logger.error("Failed to start Prism.", e);
            }
        }

        if (config.enableBedrock()) {
            logger.info("Flux gateway initialized on port " + config.bedrockPort() + ".");
        }

        tickHandle = scheduler.scheduleRepeating(
                this::tick,
                0L,
                Math.max(1L, 1000L / Math.max(1, config.tickRate())),
                TimeUnit.MILLISECONDS
        );

        eventBus.post(new ServerLifecycleEvent(ServerLifecycleEvent.Phase.STARTED, Instant.now()));
        logger.info("Lumina is ready.");
    }

    private void tick() {
        long tick = tickCounter.incrementAndGet();
        eventBus.post(new ServerTickEvent(tick, Instant.now()));
        if (config.debug() && tick % Math.max(1, config.tickRate()) == 0) {
            logger.debug("Tick " + tick);
        }
    }

    @Override
    public synchronized void stop() {
        if (!started.compareAndSet(true, false)) {
            return;
        }

        logger.info("Stopping Lumina...");
        eventBus.post(new ServerLifecycleEvent(ServerLifecycleEvent.Phase.STOPPING, Instant.now()));

        if (tickHandle != null) {
            tickHandle.cancel();
            tickHandle = null;
        }

        try {
            prismRuntime.unload();
        } catch (Exception e) {
            logger.error("Error while shutting down Prism.", e);
        }

        scheduler.shutdown();
        eventBus.post(new ServerLifecycleEvent(ServerLifecycleEvent.Phase.STOPPED, Instant.now()));
        logger.info("Lumina stopped.");
    }
}
