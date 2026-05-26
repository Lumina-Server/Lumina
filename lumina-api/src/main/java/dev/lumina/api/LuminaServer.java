// lumina-api/src/main/java/dev/lumina/api/LuminaServer.java
package dev.lumina.api;

import dev.lumina.command.CommandManager;
import dev.lumina.entity.EntityManager;
import dev.lumina.event.EventBus;
import dev.lumina.logging.LuminaLogger;
import dev.lumina.scheduler.Scheduler;
import dev.lumina.world.WorldManager;

public interface LuminaServer {
    LuminaLogger logger();
    Scheduler scheduler();
    EventBus eventBus();
    WorldManager worlds();
    EntityManager entities();
    CommandManager commands();
    void start();
    void stop();
}
