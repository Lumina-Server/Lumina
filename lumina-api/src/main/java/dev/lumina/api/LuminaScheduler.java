// lumina-api/src/main/java/dev/lumina/api/LuminaScheduler.java
package dev.lumina.api;

import dev.lumina.scheduler.TaskHandle;

import java.util.concurrent.TimeUnit;

public interface LuminaScheduler {
    TaskHandle schedule(Runnable task);
    TaskHandle scheduleDelayed(Runnable task, long delay, TimeUnit unit);
    TaskHandle scheduleRepeating(Runnable task, long initialDelay, long period, TimeUnit unit);
}
