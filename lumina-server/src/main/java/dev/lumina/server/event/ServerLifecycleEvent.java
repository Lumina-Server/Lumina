package dev.lumina.server.event;

import dev.lumina.event.Event;

import java.time.Instant;

public record ServerLifecycleEvent(Phase phase, Instant timestamp) implements Event {
    public enum Phase {
        STARTING,
        STARTED,
        STOPPING,
        STOPPED
    }
}
