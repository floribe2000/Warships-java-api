package de.floribe2000.warships_java.api;

import de.floribe2000.warships_java.requests.SimpleRateLimiter;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides a basic connector to the api.
 * <p>This class is used to provide the api key for all requests.</p>
 *
 * @author floribe2000
 */
public class ApiBuilder {

    /**
     * A logger to log events about this class
     */
    private static final Logger LOG = LoggerFactory.getLogger("ApiBuilder");

    /**
     * The current game version for this library
     */
    @Getter
    private static final VersionDetails currentVersion = new VersionDetails(0, 9, 2);

    /**
     * The instance of the ApiBuiler
     */
    @Getter
    private static ApiBuilder instance = new ApiBuilder();

    /**
     * The api key that is used to connect to the wargaming api
     */
    @Getter
    private String apiKey = null;

    /**
     * Creates an ApiBuilder instance to be used for api requests.
     *
     * @param apiKey the api key to use when connecting to the wargaming api
     */
    public static void createInstance(String apiKey) {
        createInstance(apiKey, true, true);
    }

    /**
     * Creates an ApiBuilder instance to be used for api requests.
     *
     * @param apiKey      the api key to use when connecting to the wargaming api
     * @param ignoreError a boolean to determine if an existing instance might be used
     */
    public static void createInstance(String apiKey, boolean ignoreError) {
        createInstance(apiKey, ignoreError, true);
    }

    /**
     * Creates an ApiBuilder instance to be used for api requests.
     *
     * @param apiKey           the api key to use when connecting to the wargaming api
     * @param ignoreError      a boolean to determine if an existing instance might be used
     * @param rateLimitEnabled a boolean to determine if rate limiting should be enabled. It is recommended to set this to true!
     * @throws IllegalStateException if there is already an instance defined and ignoreError is set to false
     */
    public static void createInstance(String apiKey, boolean ignoreError, boolean rateLimitEnabled) {
        if (rateLimitEnabled) {
            SimpleRateLimiter.enable();
        }
        try {
            if (instance.getApiKey() == null) {
                instance.apiKey = apiKey;
            } else {
                throw new IllegalStateException("You can't set your api key more than once.");
            }
        } catch (Exception e) {
            if (ignoreError) {
                LOG.warn("api key was already set. Ignoring exception", e);
            } else {
                throw new IllegalStateException(e.getMessage());
            }
        }
    }

    public static String getApiKeyAsParam() {
        if (instance.getApiKey() == null) {
            throw new IllegalStateException("The api key must not be null");
        }
        return "?application_id=" + instance.getApiKey();
    }
}
