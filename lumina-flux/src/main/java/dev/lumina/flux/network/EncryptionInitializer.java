package dev.lumina.flux.network;

import java.util.Objects;

public final class EncryptionInitializer {
    private final boolean enabled;

    public EncryptionInitializer(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean enabled() {
        return enabled;
    }

    public String describe() {
        return enabled ? "floodgate-auth-enabled" : "encryption-disabled";
    }

    @Override
    public String toString() {
        return describe();
    }
}
