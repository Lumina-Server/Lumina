// lumina-entity/src/main/java/dev/lumina/entity/MobEntity.java
package dev.lumina.entity;

public abstract class MobEntity extends LivingEntity {
    private boolean aggressive;

    public boolean aggressive() {
        return aggressive;
    }

    public void setAggressive(boolean aggressive) {
        this.aggressive = aggressive;
    }
}
