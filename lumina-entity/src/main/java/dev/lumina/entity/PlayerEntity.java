// lumina-entity/src/main/java/dev/lumina/entity/PlayerEntity.java
package dev.lumina.entity;

import java.util.UUID;

public final class PlayerEntity extends LivingEntity {
    private final String username;
    private final UUID profileId;

    public PlayerEntity(UUID uuid) {
        this("Unknown", uuid, uuid);
    }

    @Override
    public void tick() {
        // player tick logic
    }

    public PlayerEntity(String username, UUID uuid, UUID profileId) {
        super(profileId, EntityType.PLAYER);

        this.username = username;
        this.profileId = profileId;
    }

    public String username() {
        return username;
    }

    public UUID profileId() {
        return profileId;
    }
}
