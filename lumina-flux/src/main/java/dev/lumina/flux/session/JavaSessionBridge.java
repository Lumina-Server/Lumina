package dev.lumina.flux.session;

import java.util.Objects;
import java.util.UUID;

public final class JavaSessionBridge {
    private final SessionManager sessionManager;

    public JavaSessionBridge(SessionManager sessionManager) {
        this.sessionManager = Objects.requireNonNull(sessionManager, "sessionManager");
    }

    public BedrockSession attach(BedrockSession session) {
        sessionManager.register(session);
        return session;
    }

    public void detach(UUID uuid) {
        sessionManager.remove(uuid);
    }

    public void updateEntityId(UUID uuid, long entityId) {
        sessionManager.find(uuid).ifPresent(session -> session.entityId(entityId));
    }

    public void keepAlive(UUID uuid, long timestamp) {
        sessionManager.find(uuid).ifPresent(session -> session.lastKeepAlive(timestamp));
    }
}
