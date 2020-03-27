package de.floribe2000.warships_java.requests;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by using code from <a href="https://dzone.com/articles/basic-api-rate-limiting">DZone</a>.
 * <p>Limits the requests per second to 10 requests all 2 seconds. Usually the rate limit is 10 per second but the wargaming api often throws errors in this case.</p>
 * <p>Currently work in progress!</p>
 */
//TODO
public class SimpleRateLimiter {

    private static final Semaphore semaphore = new Semaphore(10);

    private static int maxPermits = 10;

    private static TimeUnit timePeriod = TimeUnit.SECONDS;

    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private static AtomicBoolean initialized = new AtomicBoolean(false);


    public static void waitForPermit() {
        if (!initialized.get()) {
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
        initialized.set(false);
    }


    private static void schedulePermitReplenishment() {
        if (initialized.get()) return;

        scheduler.schedule(() -> semaphore.release(maxPermits - semaphore.availablePermits()), 2, timePeriod);
        initialized.set(true);
    }
}


