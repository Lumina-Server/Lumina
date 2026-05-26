// lumina-scheduler/src/main/java/dev/lumina/scheduler/SchedulerImpl.java
package dev.lumina.scheduler;

import java.util.Objects;
import java.util.concurrent.*;

public final class SchedulerImpl implements Scheduler {
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(
            Math.max(2, Runtime.getRuntime().availableProcessors() / 2),
            r -> {
                Thread t = new Thread(r, "lumina-scheduler");
                t.setDaemon(true);
                return t;
            });

    @Override
    public TaskHandle schedule(Runnable task) {
        Objects.requireNonNull(task, "task");
        Future<?> future = executor.submit(task);
        return new FutureTaskHandle(future);
    }

    @Override
    public TaskHandle scheduleDelayed(Runnable task, long delay, TimeUnit unit) {
        Future<?> future = executor.schedule(task, delay, unit);
        return new FutureTaskHandle(future);
    }

    @Override
    public TaskHandle scheduleRepeating(Runnable task, long initialDelay, long period, TimeUnit unit) {
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(task, initialDelay, period, unit);
        return new FutureTaskHandle(future);
    }

    @Override
    public void shutdown() {
        executor.shutdownNow();
    }

    public static final class FutureTaskHandle implements TaskHandle {
        private final Future<?> future;

        public FutureTaskHandle(Future<?> future) {
            this.future = future;
        }

        @Override
        public void cancel() {
            future.cancel(false);
        }

        @Override
        public boolean cancelled() {
            return future.isCancelled();
        }
    }
}
