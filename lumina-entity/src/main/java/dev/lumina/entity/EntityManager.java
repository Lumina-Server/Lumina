package dev.lumina.entity;

import java.util.Collection;
import java.util.UUID;

public interface EntityManager {

    /**
     * Register entity into Lumina registry.
     */
    void register(Entity entity);

    /**
     * Unregister entity from registry.
     */
    void unregister(UUID uuid);

    /**
     * Get entity by UUID.
     */
    Entity get(UUID uuid);

    /**
     * Check entity exists.
     */
    boolean exists(UUID uuid);

    /**
     * Get all loaded entities.
     */
    Collection<Entity> all();

    /**
     * Get online player count.
     */
    int onlinePlayers();

    /**
     * Total loaded entities.
     */
    int entityCount();

    /**
     * Tick all entities.
     */
    void tick();

    /**
     * Save all entities.
     */
    void save();

    /**
     * Remove all entities.
     */
    void shutdown();

    /**
     * Spawn entity.
     */
    Entity spawn(EntityType type, double x, double y, double z);

    /**
     * Remove entity.
     */
    void remove(Entity entity);

    /**
     * Find nearest entity.
     */
    Entity nearest(double x, double y, double z, double radius);

    /**
     * Broadcast packet/update to entities.
     */
    void broadcast(Object packet);

    /**
     * Async entity ticking enabled?
     */
    boolean asyncTicking();

    /**
     * Entity cache enabled?
     */
    boolean cacheEnabled();
}
