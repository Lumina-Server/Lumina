// lumina-event/src/main/java/dev/lumina/event/EventBus.java
package dev.lumina.event;

public interface EventBus {
    <T extends Event> void register(Class<T> type, EventPriority priority, Listener<T> listener);
    void post(Event event);
}
