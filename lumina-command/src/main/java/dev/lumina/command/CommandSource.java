// lumina-command/src/main/java/dev/lumina/command/CommandSource.java
package dev.lumina.command;

public interface CommandSource {
    void sendMessage(String message);
    String name();
}
