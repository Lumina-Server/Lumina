// lumina-command/src/main/java/dev/lumina/command/CommandManager.java
package dev.lumina.command;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface CommandManager {
    Map<String, CommandNode> commands = new ConcurrentHashMap<>();

    default void register(CommandNode command) {
        commands.put(command.name().toLowerCase(), command);
    }

    default CommandDispatcher dispatcher() {
        return new CommandDispatcher(commands);
    }
}
