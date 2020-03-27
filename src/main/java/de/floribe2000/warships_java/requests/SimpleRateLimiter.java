package de.floribe2000.warships_java.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A rate limiter to avoid request limit errors while accessing the api.
 * <p>Limits the requests per second to 10 requests per 1.2 seconds.</p>
 *
 * <p>If disabled, you have to handle the rate limit on your own.</p>
 */
public class SimpleRateLimiter {

    private static ApiType type = ApiType.CLIENT;

    private static Semaphore semaphore = new Semaphore(type.getRateLimit());

    private static AtomicBoolean enabled = new AtomicBoolean(false);

    private static Timer timer = new Timer();

    /**
     * Waits for a permit to execute the request.
     */
    public static void waitForPermit() {
        if (!enabled.get()) {
            return;
        }
        try {
            semaphore.acquire();
            scheduleDelete();
        } catch (InterruptedException e) {
            throw new IllegalStateException("Failed to wait for semaphore.");
        }
    }

    /**
     * Schedules the release task for a previous acquire task.
     */
    private static void scheduleDelete() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                semaphore.release();
            }
        }, 1200);
    }

    /**
     * Tells if the rate limiter is enabled or not.
     *
     * @return true if enabled, false if not
     */
    public static boolean isEnabled() {
        return enabled.get();
    }

    /**
     * Enables the rate limiter.
     */
    public static void enable() {
        enabled.set(true);
    }

    /**
     * Tries to disable the rate limiter.
     * <p>To disable the rate limiter there must be no threads waiting for it!</p>
     *
     * @return true if disabling was successful, false if not
     */
    public static boolean disable() {
        if (semaphore.getQueueLength() < 1) {
            enabled.set(false);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Allows to switch the api client type between client and server to change the request limit according to the selected settings.
     * <p>To change the apy type, the rate limiter has to be disabled first!</p>
     *
     * @param type the ApiType to set
     * @return true if the change was successful, false if not
     */
    public static boolean setClientType(@NonNull ApiType type) {
        if (enabled.get()) {
            return false;
        } else {
            SimpleRateLimiter.type = type;
            semaphore = new Semaphore(type.getRateLimit());
            return true;
        }
    }

    @AllArgsConstructor
    @Getter
    public enum ApiType {
        CLIENT(10),
        SERVER(20);

        private int rateLimit;
    }
}


