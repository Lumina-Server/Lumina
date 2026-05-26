package dev.lumina.flux.session;

import java.net.SocketAddress;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class SessionManager {
    private final Map<UUID, BedrockSession> sessionsById = new ConcurrentHashMap<>();
    private final Map<String, BedrockSession> sessionsByAddress = new ConcurrentHashMap<>();

    public BedrockSession create(UUID uuid, String xuid, String username, SocketAddress address) {
        BedrockSession session = new BedrockSession(uuid, xuid, username, address);
        register(session);
        return session;
    }

    public void register(BedrockSession session) {
        sessionsById.put(session.uuid(), session);
        sessionsByAddress.put(key(session.remoteAddress()), session);
    }

    public Optional<BedrockSession> find(UUID uuid) {
        return Optional.ofNullable(sessionsById.get(uuid));
    }

    public Optional<BedrockSession> find(SocketAddress address) {
        return Optional.ofNullable(sessionsByAddress.get(key(address)));
    }

    public void remove(UUID uuid) {
        BedrockSession removed = sessionsById.remove(uuid);
        if (removed != null) {
            sessionsByAddress.remove(key(removed.remoteAddress()));
        }
    }

    public Collection<BedrockSession> all() {
        return Map.copyOf(sessionsById).values();
    }

    public void clear() {
        sessionsById.clear();
        sessionsByAddress.clear();
    }

    private static String key(SocketAddress address) {
        return address == null ? "" : address.toString();
    }
}
