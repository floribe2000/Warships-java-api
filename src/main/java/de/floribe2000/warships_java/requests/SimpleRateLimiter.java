package de.floribe2000.warships_java.requests;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by using code from <a href="https://dzone.com/articles/basic-api-rate-limiting">DZone</a>.
 * <p>Limits the requests per second to 20.</p>
 */
public class SimpleRateLimiter {

    private static final Semaphore semaphore = new Semaphore(20);

    private static int maxPermits = 20;

    private static TimeUnit timePeriod = TimeUnit.SECONDS;

    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private static boolean initialized = false;


    public static boolean tryAcquire() {
        if (!initialized) {
            schedulePermitReplenishment();
        }
        return semaphore.tryAcquire();
    }

    public static void waitForPermit() {
        if (!initialized) {
            schedulePermitReplenishment();
        }
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new IllegalStateException("Failed to wait for semaphore.");
        }
    }


    public static void stop() {
        scheduler.shutdownNow();
        initialized = false;
    }


    private static void schedulePermitReplenishment() {
        if (initialized) return;

        scheduler.schedule(() -> {
            semaphore.release(maxPermits - semaphore.availablePermits());
        }, 1, timePeriod);
        initialized = true;
    }
}
