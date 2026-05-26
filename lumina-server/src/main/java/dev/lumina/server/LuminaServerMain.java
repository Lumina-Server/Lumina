package dev.lumina.server;

import java.nio.file.Path;

public final class LuminaServerMain {
    private LuminaServerMain() {}

    public static void main(String[] args) throws Exception {
        Path root = args.length > 0 && !args[0].isBlank() ? Path.of(args[0]) : Path.of(".");
        LuminaServerBootstrap bootstrap = new LuminaServerBootstrap();
        LuminaServerImpl server = bootstrap.bootstrap(root);
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(server::stop, "lumina-shutdown"));
    }
}
