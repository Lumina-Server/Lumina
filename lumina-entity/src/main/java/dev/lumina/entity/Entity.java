package dev.lumina.entity;

import java.util.UUID;

public interface Entity {

    UUID uuid();

    EntityType type();

    void tick();

    default boolean alive() {
        return true;
    }

    default void remove() {
    }
}
