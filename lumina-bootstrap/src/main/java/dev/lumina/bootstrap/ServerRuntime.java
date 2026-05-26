// lumina-bootstrap/src/main/java/dev/lumina/bootstrap/ServerRuntime.java
package dev.lumina.bootstrap;

import dev.lumina.api.LuminaServer;

public final class ServerRuntime {
    private final LuminaServer server;

    public ServerRuntime(LuminaServer server) {
        this.server = server;
    }

    public LuminaServer server() {
        return server;
    }
}
