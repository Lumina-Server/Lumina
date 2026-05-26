package dev.lumina.flux.session;

import java.time.Instant;
import java.util.UUID;

public record AuthSession(
        UUID uuid,
        String xuid,
        String username,
        String identityToken,
        Instant authenticatedAt
) {}
