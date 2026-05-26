package dev.lumina.server.console;

public final class ConsoleBootstrap {
    private final ConsoleReader reader = new ConsoleReader();
    private final ConsoleCommandHandler handler = new ConsoleCommandHandler();

    public ConsoleReader reader() {
        return reader;
    }

    public ConsoleCommandHandler handler() {
        return handler;
    }
}
