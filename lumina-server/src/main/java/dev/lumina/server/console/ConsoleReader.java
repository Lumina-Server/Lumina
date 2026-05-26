package dev.lumina.server.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public final class ConsoleReader {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String readLine() {
        try {
            return reader.readLine();
        } catch (Exception e) {
            return null;
        }
    }
}
