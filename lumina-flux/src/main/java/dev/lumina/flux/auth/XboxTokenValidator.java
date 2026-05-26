package dev.lumina.flux.auth;

import java.util.Objects;

public final class XboxTokenValidator {

    public boolean isValid(String token) {
        return token != null && !token.isBlank() && token.length() >= 8;
    }

    public String xuidFrom(String token) {
        if (!isValid(token)) {
            throw new IllegalArgumentException("Invalid token");
        }
        return "xuid:" + Integer.toHexString(token.hashCode());
    }

    public String usernameFrom(String token) {
        if (!isValid(token)) {
            throw new IllegalArgumentException("Invalid token");
        }
        return "bedrock_" + Math.abs(token.hashCode());
    }
}
