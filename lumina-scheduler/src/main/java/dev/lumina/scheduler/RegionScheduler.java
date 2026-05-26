// lumina-scheduler/src/main/java/dev/lumina/scheduler/RegionScheduler.java
package dev.lumina.scheduler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class RegionScheduler {
    private final Map<Long, ExecutorService> regions = new ConcurrentHashMap<>();

    public ExecutorService executorFor(int regionX, int regionZ) {
        long key = ((long) regionX << 32) ^ (regionZ & 0xffffffffL);
        return regions.computeIfAbsent(key, k -> Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r, "lumina-region-" + regionX + "-" + regionZ);
            t.setDaemon(true);
            return t;
        }));
    }

    public void shutdown() {
        regions.values().forEach(ExecutorService::shutdownNow);
        regions.clear();
    }
}
