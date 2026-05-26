package dev.lumina.prism;

import dev.lumina.scheduler.Scheduler;
import dev.lumina.scheduler.TaskHandle;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public final class PrismSchedulerBridge {
    private final Scheduler scheduler;

    public PrismSchedulerBridge(Scheduler scheduler) {
        this.scheduler = Objects.requireNonNull(scheduler, "scheduler");
    }

    public TaskHandle runTask(Runnable task) {
        return scheduler.schedule(task);
    }

    public TaskHandle runLater(Runnable task, long delay, TimeUnit unit) {
        return scheduler.scheduleDelayed(task, delay, unit);
    }

    public TaskHandle runTimer(Runnable task, long initialDelay, long period, TimeUnit unit) {
        return scheduler.scheduleRepeating(task, initialDelay, period, unit);
    }
}
