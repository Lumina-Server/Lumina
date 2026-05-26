// lumina-event/src/main/java/dev/lumina/event/EventBusImpl.java
package dev.lumina.event;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public final class EventBusImpl implements EventBus {
    private final Map<Class<?>, EnumMap<EventPriority, List<Listener<?>>>> listeners = new ConcurrentHashMap<>();

    @Override
    public <T extends Event> void register(Class<T> type, EventPriority priority, Listener<T> listener) {
        listeners.computeIfAbsent(type, k -> new EnumMap<>(EventPriority.class))
                .computeIfAbsent(priority, k -> new ArrayList<>())
                .add(listener);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void post(Event event) {
        EnumMap<EventPriority, List<Listener<?>>> map = listeners.get(event.getClass());
        if (map == null) return;
        for (EventPriority priority : EventPriority.values()) {
            List<Listener<?>> list = map.get(priority);
            if (list == null) continue;
            for (Listener<?> listener : list) {
                try {
                    ((Listener<Event>) listener).handle(event);
                } catch (Exception ignored) {
                }
            }
        }
    }
}
