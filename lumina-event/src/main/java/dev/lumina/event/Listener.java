// lumina-event/src/main/java/dev/lumina/event/Listener.java
package dev.lumina.event;

@FunctionalInterface
public interface Listener<T extends Event> {
    void handle(T event) throws Exception;
}
