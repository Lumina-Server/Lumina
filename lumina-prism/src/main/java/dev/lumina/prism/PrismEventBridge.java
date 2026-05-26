package dev.lumina.prism;

import dev.lumina.event.Event;
import dev.lumina.event.EventBus;
import dev.lumina.event.EventPriority;
import dev.lumina.event.Listener;

import java.util.Objects;

public final class PrismEventBridge {
    private final EventBus eventBus;

    public PrismEventBridge(EventBus eventBus) {
        this.eventBus = Objects.requireNonNull(eventBus, "eventBus");
    }

    public <T extends Event> void listen(Class<T> type, EventPriority priority, Listener<T> listener) {
        eventBus.register(type, priority, listener);
    }

    public void fire(Event event) {
        eventBus.post(event);
    }
}
