// lumina-scheduler/src/main/java/dev/lumina/scheduler/TaskHandle.java
package dev.lumina.scheduler;

public interface TaskHandle {
    void cancel();
    boolean cancelled();
}
