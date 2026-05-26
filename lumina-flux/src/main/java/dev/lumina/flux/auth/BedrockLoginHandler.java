package dev.lumina.flux.auth;

import dev.lumina.flux.session.AuthSession;
import dev.lumina.flux.session.BedrockSession;
import dev.lumina.flux.session.SessionManager;

import java.net.SocketAddress;
import java.util.Optional;

public final class BedrockLoginHandler {
    private final FloodgateAuth auth;
    private final SessionManager sessionManager;

    public BedrockLoginHandler(FloodgateAuth auth, SessionManager sessionManager) {
        this.auth = auth;
        this.sessionManager = sessionManager;
    }

    public Optional<BedrockSession> login(String token, SocketAddress address) {
        Optional<AuthSession> session = auth.authenticate(token);
        if (session.isEmpty()) {
            return Optional.empty();
        }

        AuthSession a = session.get();
        BedrockSession bedrockSession = sessionManager.create(a.uuid(), a.xuid(), a.username(), address);
        return Optional.of(bedrockSession);
    }
}
