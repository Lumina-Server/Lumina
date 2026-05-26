// lumina-scheduler/src/main/java/dev/lumina/scheduler/Scheduler.java
package dev.lumina.scheduler;

import java.util.concurrent.TimeUnit;

public interface Scheduler {
    TaskHandle schedule(Runnable task);
    TaskHandle scheduleDelayed(Runnable task, long delay, TimeUnit unit);
    TaskHandle scheduleRepeating(Runnable task, long initialDelay, long period, TimeUnit unit);
    void shutdown();
}
