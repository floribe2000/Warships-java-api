package de.floribe2000.warships_java.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A rate limiter to avoid request limit errors while accessing the api.
 * <p>Limits the requests per second to 10 requests per second.</p>
 * <p>In theory a limit of 10 requests per seconds should be fine but this causes problems with the WG api.</p>
 *
 * <p>If disabled, you have to handle the rate limit on your own.</p>
 */
public class SimpleRateLimiter implements Closeable {

    private ApiType type;

    private Semaphore semaphore;

    private final AtomicBoolean enabled = new AtomicBoolean(false);

    private final Timer timer = new Timer();

    private final long resetDelay = 1000;

    public SimpleRateLimiter(boolean enabled, @NonNull ApiType type) {
        this.type = type;
        semaphore = new Semaphore(this.type.getRateLimit());
        this.enabled.set(enabled);
    }

    /**
     * Waits for a permit to execute the request.
     */
    public void waitForPermit() {
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

    public static void waitForPermit(SimpleRateLimiter limiter) {
        if (limiter == null) {
            return;
        }
        limiter.waitForPermit();
    }

    /**
     * Schedules the release task for a previous acquire task.
     */
    private void scheduleDelete() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                semaphore.release();
            }
        }, resetDelay);
    }

    /**
     * Tells if the rate limiter is enabled or not.
     *
     * @return true if enabled, false if not
     */
    public boolean isEnabled() {
        return enabled.get();
    }

    /**
     * Enables the rate limiter.
     */
    public void enable() {
        enabled.set(true);
    }

    /**
     * Tries to disable the rate limiter.
     * <p>To disable the rate limiter there must be no threads waiting for it!</p>
     *
     * @return true if disabling was successful, false if not
     */
    public boolean disable() {
        if (semaphore.getQueueLength() < 1) {
            enabled.set(false);
            return true;
        } else {
            return false;
        }
    }

    public InputStream connectToApi(String url) throws IOException {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new IllegalStateException("Failed to wait for semaphore.");
        }
        InputStream stream = new URL(url).openStream();
        scheduleDelete();
        return stream;
    }

    /**
     * Allows to switch the api client type between client and server to change the request limit according to the selected settings.
     * <p>To change the apy type, the rate limiter has to be disabled first!</p>
     *
     * @param type the ApiType to set
     * @return true if the change was successful, false if not
     */
    public boolean setClientType(@NonNull ApiType type) {
        if (enabled.get()) {
            return false;
        } else {
            this.type = type;
            semaphore = new Semaphore(type.getRateLimit());
            return true;
        }
    }

    @Override
    public void close() {
        enabled.set(false);
        try {
            semaphore.acquire();
        } catch (Exception e) {
            //
        }
        timer.cancel();
    }

    @AllArgsConstructor
    @Getter
    public enum ApiType {
        CLIENT(10),
        SERVER(20);

        private int rateLimit;
    }
}


