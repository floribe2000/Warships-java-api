package de.floribe2000.warships_java.direct.api;

import de.floribe2000.warships_java.requests.SimpleRateLimiter;
import de.floribe2000.warships_java.utilities.AbstractRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides a basic connector to the api.
 * <p>This class is used to provide the api key for all requests.</p>
 *
 * @author floribe2000
 */
public final class ApiBuilder {

    /**
     * A logger to log events about this class
     */
    private static final Logger LOG = LoggerFactory.getLogger("ApiBuilder");

    /**
     * The current game version for this library
     */
    private static final VersionDetails currentVersion = new VersionDetails(0, 9, 3);

    /**
     * The last automatically assigned id for a new instance without specified identifier
     */
    private static int lastNum = 0;

    /**
     * The default api type for new api instances
     */
    private static SimpleRateLimiter.ApiType defaultType = SimpleRateLimiter.ApiType.CLIENT;

    /**
     * The instances of the ApiBuilder
     */
    private static final Map<String, ApiBuilder> instances = Collections.synchronizedMap(new HashMap<>());

    /**
     * The unique identifier of the primary api instance
     */
    private static String primaryInstance = null;


    /**
     * The rate limiter instance linked to this api builder instance
     */
    private final SimpleRateLimiter rateLimiter;

    /**
     * The unique identifier of this instance
     */
    private final String instanceName;

    /**
     * The api key that is used to connect to the wargaming api
     */
    private final String apiKey;

    public ApiBuilder(SimpleRateLimiter rateLimiter, String instanceName, String apiKey) {
        this.rateLimiter = rateLimiter;
        this.instanceName = instanceName;
        this.apiKey = apiKey;
    }

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
     * @param apiKey the api key to use when connecting to the wargaming api
     * @param type   the {@link de.floribe2000.warships_java.requests.SimpleRateLimiter.ApiType ApiType} of the new instance (client or server)
     * @throws IllegalStateException if there is already an instance defined and ignoreError is set to false
     */
    public static void createInstance(String apiKey, SimpleRateLimiter.ApiType type) {
        String apiId = "API_" + lastNum++;
        createInstance(apiKey, type, apiId);
    }

    /**
     * Creates an ApiBuilder instance to be used for api requests.
     *
     * @param apiKey       the api key to use when connecting to the wargaming api
     * @param type         the {@link de.floribe2000.warships_java.requests.SimpleRateLimiter.ApiType ApiType} of the new instance (client or server)
     * @param instanceName the name of the instance to create
     * @throws IllegalStateException if there is already an instance defined and ignoreError is set to false
     */
    public static void createInstance(String apiKey, SimpleRateLimiter.ApiType type, String instanceName) {
        createInstance(apiKey, true, instanceName, type);
    }

    /**
     * Creates an ApiBuilder instance to be used for api requests.
     *
     * @param apiKey           the api key to use when connecting to the wargaming api
     * @param rateLimitEnabled a boolean to determine if rate limiting should be enabled. It is recommended to set this to true!
     * @throws IllegalStateException if there is already an instance defined and ignoreError is set to false
     */
    public static void createInstance(String apiKey, boolean rateLimitEnabled, String instanceName) {
        createInstance(apiKey, rateLimitEnabled, instanceName, defaultType);
    }

    /**
     * Creates an ApiBuilder instance to be used for api requests.
     *
     * @param apiKey           the api key to use when connecting to the wargaming api
     * @param rateLimitEnabled a boolean to determine if rate limiting should be enabled. It is recommended to set this to true!
     * @param instanceName     the name of the instance to create
     * @param type             the {@link de.floribe2000.warships_java.requests.SimpleRateLimiter.ApiType ApiType} of the new instance (client or server)
     * @throws IllegalStateException if there is already an instance defined and ignoreError is set to false
     */
    public static void createInstance(String apiKey, boolean rateLimitEnabled, String instanceName, SimpleRateLimiter.ApiType type) {
        if (instances.get(instanceName) != null) {
            return;
        }
        ApiBuilder instance = new ApiBuilder(new SimpleRateLimiter(rateLimitEnabled, type), instanceName, apiKey);
        instances.put(instanceName, instance);
        if (primaryInstance == null) {
            primaryInstance = instanceName;
        }
    }

    /**
     * Creates a new ApiBuilder instance if there is no active existing instance.
     *
     * @param apiKey the api key to use when connecting to the wargaming api
     */
    public static void createInstanceIfNoneExists(String apiKey) {
        if (instances.isEmpty()) {
            createInstance(apiKey);
        }
    }

    /**
     * Creates a new ApiBuilder instance if there is no active existing instance.
     *
     * @param apiKey the api key to use when connecting to the wargaming api
     * @param type   the {@link de.floribe2000.warships_java.requests.SimpleRateLimiter.ApiType ApiType} of the new instance (client or server)
     */
    public static void createInstanceIfNoneExists(String apiKey, SimpleRateLimiter.ApiType type) {
        if (instances.isEmpty()) {
            createInstance(apiKey, type);
        }
    }

    /**
     * A method to get the api key of this instance as url parameter that can be used as first parameter of a request url
     *
     * @return the api key as first url parameter
     */
    public String getApiKeyAsParam() {
        return "?application_id=" + apiKey;
    }

    /**
     * Returns the api key of an instance as url param.
     *
     * @param instanceName the instance for the request
     * @return the api key of the instance
     * @throws NullPointerException If there is no such instance, an exception is thrown.
     * @see ApiBuilder#getApiKeyAsParam()
     */
    public static String getApiKeyAsParam(String instanceName) {
        if (instances.isEmpty()) {
            return null;
        }
        if (instanceName == null) {
            instanceName = primaryInstance;
        }
        return instances.get(instanceName).getApiKeyAsParam();
    }

    /**
     * Returns an instance with the specified identifier
     *
     * @param instanceName the identifier of the requested instance
     * @return the instance or null if there is no such instance
     */
    public static ApiBuilder getInstanceWithName(String instanceName) {
        if (instanceName == null) {
            instanceName = primaryInstance;
        }
        return instances.get(instanceName);
    }

    /**
     * A method to get the current number of active instances
     *
     * @return the number of active instances as integer
     */
    public static int getInstanceSize() {
        return instances.size();
    }

    /**
     * Closes the instance with the specified identifier
     *
     * @param instanceName the identifier of the instance that should be closed
     * @throws IOException If an error occurs while closing the instance
     */
    public static void closeInstance(String instanceName) throws IOException {
        instances.get(instanceName).close();
        instances.remove(instanceName);
    }

    /**
     * Closes the instance
     *
     * @throws IOException if an error occurs while closing the rate limiter
     */
    private void close() throws IOException {
        rateLimiter.close();
    }

    /**
     * Initiates a shutdown of all instances. Tries to close all instances that are currently registered.
     */
    public static void shutdown() {
        instances.forEach((key, value) -> {
            try {
                value.close();
            } catch (IOException e) {
                LOG.error("Error while shutting down instance " + value.getInstanceName(), e);
            }
        });
        instances.clear();
        primaryInstance = null;
        lastNum = 0;
        AbstractRequestService.reset();
    }

    /**
     * A method to show if there is at least 1 active instance of this class.
     *
     * @return true if there is at least an active instance, false if not
     */
    public static boolean isInitialized() {
        return instances.size() > 0;
    }

    public static VersionDetails getCurrentVersion() {
        return ApiBuilder.currentVersion;
    }

    public static SimpleRateLimiter.ApiType getDefaultType() {
        return ApiBuilder.defaultType;
    }

    public SimpleRateLimiter getRateLimiter() {
        return this.rateLimiter;
    }

    public String getInstanceName() {
        return this.instanceName;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public static void setDefaultType(SimpleRateLimiter.ApiType defaultType) {
        ApiBuilder.defaultType = defaultType;
    }
}
