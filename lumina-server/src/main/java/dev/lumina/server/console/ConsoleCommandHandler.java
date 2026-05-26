package dev.lumina.server.console;

import dev.lumina.command.CommandDispatcher;
import dev.lumina.command.CommandSource;

public final class ConsoleCommandHandler {
    public int dispatch(CommandDispatcher dispatcher, String line) {
        return dispatcher.dispatch(new ConsoleCommandSource(), line);
    }

    private static final class ConsoleCommandSource implements CommandSource {
        @Override
        public void sendMessage(String message) {
            System.out.println(message);
        }

        @Override
        public String name() {
            return "Console";
        }
    }
}
