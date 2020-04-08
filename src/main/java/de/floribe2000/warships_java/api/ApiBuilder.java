package de.floribe2000.warships_java.api;

import de.floribe2000.warships_java.requests.SimpleRateLimiter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.*;

/**
 * Provides a basic connector to the api.
 * <p>This class is used to provide the api key for all requests.</p>
 *
 * @author floribe2000
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiBuilder {

    /**
     * A logger to log events about this class
     */
    private static final Logger LOG = LoggerFactory.getLogger("ApiBuilder");

    private static int lastNum = 0;

    @Getter
    private final SimpleRateLimiter rateLimiter;

    @Getter
    private final String instanceName;

    /**
     * The current game version for this library
     */
    @Getter
    private static final VersionDetails currentVersion = new VersionDetails(0, 9, 2);

    /**
     * The instances of the ApiBuilder
     */
    private static Map<String, ApiBuilder> instances = Collections.synchronizedMap(new HashMap<>());

    private static String primaryInstance = null;

    /**
     * The api key that is used to connect to the wargaming api
     */
    @Getter
    private final String apiKey;

    /**
     * Creates an ApiBuilder instance to be used for api requests.
     *
     * @param apiKey the api key to use when connecting to the wargaming api
     */
    public static void createInstance(String apiKey) {
        createInstance(apiKey, true);
    }

    /**
     * Creates an ApiBuilder instance to be used for api requests.
     *
     * @param apiKey the api key to use when connecting to the wargaming api
     */
    public static void createInstance(String apiKey, String apiId) {
        createInstance(apiKey, true, apiId);
    }

    /**
     * Creates an ApiBuilder instance to be used for api requests.
     *
     * @param apiKey           the api key to use when connecting to the wargaming api
     * @param rateLimitEnabled a boolean to determine if rate limiting should be enabled. It is recommended to set this to true!
     * @throws IllegalStateException if there is already an instance defined and ignoreError is set to false
     */
    public static void createInstance(String apiKey, boolean rateLimitEnabled) {
        String apiId = "API_" + lastNum++;
        createInstance(apiKey, rateLimitEnabled, apiId);
    }

    /**
     * Creates an ApiBuilder instance to be used for api requests.
     *
     * @param apiKey           the api key to use when connecting to the wargaming api
     * @param rateLimitEnabled a boolean to determine if rate limiting should be enabled. It is recommended to set this to true!
     * @throws IllegalStateException if there is already an instance defined and ignoreError is set to false
     */
    public static ApiBuilder createInstance(String apiKey, boolean rateLimitEnabled, String instanceName) {
        if (instances.get(instanceName) != null) {
            return instances.get(instanceName);
        }
        ApiBuilder instance = new ApiBuilder(new SimpleRateLimiter(rateLimitEnabled), instanceName, apiKey);
        instances.put(instanceName, instance);
        if (primaryInstance == null) {
            primaryInstance = instanceName;
        }
        return instance;
    }

    public String getApiKeyAsParam() {
        return "?application_id=" + apiKey;
    }

    public static String getApiKeyAsParam(String instanceName) {
        if (instanceName == null) {
            instanceName = primaryInstance;
        }
        return instances.get(instanceName).getApiKeyAsParam();
    }

    public static ApiBuilder getInstanceWithName(String instanceName) {
        if (instanceName == null) {
            instanceName = primaryInstance;
        }
        return instances.get(instanceName);
    }

    public static int getInstanceSize() {
        return instances.size();
    }

    public static void closeInstance(String instanceName) throws IOException {
        instances.get(instanceName).close();
        instances.remove(instanceName);
    }

    private void close() throws IOException {
        rateLimiter.close();
    }

    public void shutdown() throws IOException {
        instances.forEach((key, value) -> {
            try {
                value.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        instances.clear();
    }
}
