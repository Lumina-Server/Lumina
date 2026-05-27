package dev.lumina.entity;

import java.util.Map;
import java.util.UUID;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public final class SimpleEntityManager implements EntityManager {

    private final Map<UUID, Entity> entities = new ConcurrentHashMap<>();

    @Override
    public void register(Entity entity) {
        entities.put(entity.uuid(), entity);
    }

    @Override
    public void unregister(UUID uuid) {
        entities.remove(uuid);
    }

    @Override
    public Entity get(UUID uuid) {
        return entities.get(uuid);
    }

    @Override
    public boolean exists(UUID uuid) {
        return entities.containsKey(uuid);
    }

    @Override
    public Collection<Entity> all() {
        return entities.values();
    }

    @Override
    public int onlinePlayers() {
        int count = 0;

        for (Entity entity : entities.values()) {
            if (entity.type() == EntityType.PLAYER) {
                count++;
            }
        }

        return count;
    }

    @Override
    public int entityCount() {
        return entities.size();
    }

    @Override
    public void tick() {
        for (Entity entity : entities.values()) {
            entity.tick();
        }
    }

    @Override
    public void save() {

    }

    @Override
    public void shutdown() {
        entities.clear();
    }

    @Override
    public Entity spawn(EntityType type, double x, double y, double z) {
        return null;
    }

    @Override
    public void remove(Entity entity) {
        unregister(entity.uuid());
    }

    @Override
    public Entity nearest(double x, double y, double z, double radius) {
        return null;
    }

    @Override
    public void broadcast(Object packet) {

    }

    @Override
    public boolean asyncTicking() {
        return true;
    }

    @Override
    public boolean cacheEnabled() {
        return true;
    }
}
