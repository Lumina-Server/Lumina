package dev.lumina.flux.session;

import dev.lumina.flux.raknet.RakNetReliability;

import java.net.SocketAddress;
import java.time.Instant;
import java.util.UUID;

public final class BedrockSession {
    private final UUID uuid;
    private final String xuid;
    private final String username;
    private final SocketAddress remoteAddress;
    private final Instant createdAt = Instant.now();

    private long entityId;
    private boolean connected = true;
    private long lastKeepAlive;
    private RakNetReliability reliability = RakNetReliability.RELIABLE_ORDERED;

    public BedrockSession(UUID uuid, String xuid, String username, SocketAddress remoteAddress) {
        this.uuid = uuid;
        this.xuid = xuid;
        this.username = username;
        this.remoteAddress = remoteAddress;
    }

    public UUID uuid() {
        return uuid;
    }

    public String xuid() {
        return xuid;
    }

    public String username() {
        return username;
    }

    public SocketAddress remoteAddress() {
        return remoteAddress;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public long entityId() {
        return entityId;
    }

    public void entityId(long entityId) {
        this.entityId = entityId;
    }

    public boolean connected() {
        return connected;
    }

    public void disconnect() {
        this.connected = false;
    }

    public long lastKeepAlive() {
        return lastKeepAlive;
    }

    public void lastKeepAlive(long lastKeepAlive) {
        this.lastKeepAlive = lastKeepAlive;
    }

    public RakNetReliability reliability() {
        return reliability;
    }

    public void reliability(RakNetReliability reliability) {
        this.reliability = reliability;
    }
}
