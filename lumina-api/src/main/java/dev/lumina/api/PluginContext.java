// lumina-api/src/main/java/dev/lumina/api/PluginContext.java
package dev.lumina.api;

import dev.lumina.event.EventBus;
import dev.lumina.logging.LuminaLogger;
import dev.lumina.scheduler.Scheduler;
import dev.lumina.world.WorldManager;

public interface PluginContext {
    LuminaLogger logger();
    Scheduler scheduler();
    EventBus eventBus();
    WorldManager worlds();
}
