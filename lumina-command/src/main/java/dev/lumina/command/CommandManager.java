// lumina-command/src/main/java/dev/lumina/command/CommandManager.java
package dev.lumina.command;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface CommandManager {
    private final Map<String, CommandNode> commands = new ConcurrentHashMap<>();

    public void register(CommandNode command) {
        commands.put(command.name().toLowerCase(), command);
    }

    public CommandDispatcher dispatcher() {
        return new CommandDispatcher(commands);
    }
}
