// lumina-logging/src/main/java/dev/lumina/logging/LuminaLogger.java
package dev.lumina.logging;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class LuminaLogger {
    private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    private final String name;

    public LuminaLogger(String name) {
        this.name = name;
    }

    public void trace(String message) { log(LogLevel.TRACE, message, null); }
    public void debug(String message) { log(LogLevel.DEBUG, message, null); }
    public void info(String message)  { log(LogLevel.INFO, message, null); }
    public void warn(String message)  { log(LogLevel.WARN, message, null); }
    public void error(String message) { log(LogLevel.ERROR, message, null); }
    public void error(String message, Throwable error) { log(LogLevel.ERROR, message, error); }

    public void log(LogLevel level, String message, Throwable error) {
        String line = "[" + TIME.format(Instant.now()) + "] [" + level + "] [" + name + "] " + message;
        if (level.ordinal() >= LogLevel.WARN.ordinal()) {
            System.err.println(line);
            if (error != null) error.printStackTrace(System.err);
        } else {
            System.out.println(line);
            if (error != null) error.printStackTrace(System.out);
        }
    }
}
