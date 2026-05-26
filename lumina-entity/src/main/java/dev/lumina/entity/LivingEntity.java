// lumina-entity/src/main/java/dev/lumina/entity/LivingEntity.java
package dev.lumina.entity;

public abstract class LivingEntity extends BaseEntity {
    private double health = 20.0;

    public double health() {
        return health;
    }

    public void setHealth(double health) {
        this.health = Math.max(0.0, health);
    }

    public boolean isAlive() {
        return health > 0.0;
    }
}
