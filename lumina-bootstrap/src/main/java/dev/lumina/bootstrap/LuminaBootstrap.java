// lumina-bootstrap/src/main/java/dev/lumina/bootstrap/LuminaBootstrap.java
package dev.lumina.bootstrap;

import dev.lumina.api.LuminaServer;

public final class LuminaBootstrap {
    private final LuminaServer server;

    public LuminaBootstrap(LuminaServer server) {
        this.server = server;
    }

    public void start() {
        server.start();
    }

    public void stop() {
        server.stop();
    }
}
