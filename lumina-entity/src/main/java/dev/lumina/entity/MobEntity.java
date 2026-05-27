// lumina-entity/src/main/java/dev/lumina/entity/MobEntity.java
package dev.lumina.entity;

import java.util.UUID

public abstract class MobEntity extends LivingEntity {
    protected MobEntity(UUID uuid, EntityType type) {
        super(uuid, type);
    }

    private boolean aggressive;

    public boolean aggressive() {
        return aggressive;
    }

    public void setAggressive(boolean aggressive) {
        this.aggressive = aggressive;
    }
}
