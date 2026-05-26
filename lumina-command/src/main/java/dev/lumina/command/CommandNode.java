// lumina-command/src/main/java/dev/lumina/command/CommandNode.java
package dev.lumina.command;

import java.util.List;
import java.util.function.BiFunction;

public final class CommandNode {
    private final String name;
    private final BiFunction<CommandSource, List<String>, Integer> executor;

    public CommandNode(String name, BiFunction<CommandSource, List<String>, Integer> executor) {
        this.name = name;
        this.executor = executor;
    }

    public String name() {
        return name;
    }

    public int execute(CommandSource source, List<String> args) {
        return executor.apply(source, args);
    }
}
