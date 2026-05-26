// lumina-scheduler/src/main/java/dev/lumina/scheduler/MainThreadExecutor.java
package dev.lumina.scheduler;

import java.util.concurrent.Executor;

public final class MainThreadExecutor implements Executor {
    private final Thread mainThread;

    public MainThreadExecutor(Thread mainThread) {
        this.mainThread = mainThread;
    }

    @Override
    public void execute(Runnable command) {
        if (Thread.currentThread() == mainThread) {
            command.run();
        } else {
            throw new IllegalStateException("Not on main thread");
        }
    }
}
