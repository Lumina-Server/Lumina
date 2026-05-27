// lumina-command/src/main/java/dev/lumina/command/CommandDispatcher.java
package dev.lumina.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class CommandDispatcher {
    private final Map<String, CommandNode> commands;

    public CommandDispatcher(Map<String, CommandNode> commands) {
        this.commands = Map.copyOf(commands);
    }

    public int dispatch(CommandSource source, String line) {
        String[] split = line.trim().isEmpty() ? new String[0] : line.trim().split("\\s+");
        if (split.length == 0) return 0;
        CommandNode node = commands.get(split[0].toLowerCase());
        if (node == null) {
            source.sendMessage("Unknown command: " + split[0]);
            return -1;
        }
        List<String> args = new ArrayList<>();
        for (int i = 1; i < split.length; i++) args.add(split[i]);
        return node.execute(source, args);
    }
}
