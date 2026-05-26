// lumina-entity/src/main/java/dev/lumina/entity/BaseEntity.java
package dev.lumina.entity;

import java.util.UUID;

public abstract class BaseEntity implements Entity {
    private final UUID id = UUID.randomUUID();
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    @Override
    public UUID id() {
        return id;
    }

    public double x() { return x; }
    public double y() { return y; }
    public double z() { return z; }
    public float yaw() { return yaw; }
    public float pitch() { return pitch; }

    public void teleport(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }
}
