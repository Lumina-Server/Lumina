package dev.lumina.flux.auth;

import dev.lumina.flux.session.AuthSession;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public final class FloodgateAuth {
    private final XboxTokenValidator validator = new XboxTokenValidator();

    public Optional<AuthSession> authenticate(String token) {
        if (!validator.isValid(token)) {
            return Optional.empty();
        }

        UUID uuid = UUID.nameUUIDFromBytes(token.getBytes());
        return Optional.of(new AuthSession(
                uuid,
                validator.xuidFrom(token),
                validator.usernameFrom(token),
                token,
                Instant.now()
        ));
    }

    public XboxTokenValidator validator() {
        return validator;
    }
}
