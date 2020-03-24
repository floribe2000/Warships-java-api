package de.floribe2000.warships_java.api;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiBuilder {

    private static final Logger LOG = LoggerFactory.getLogger("ApiBuilder");

    /**
     * The current game version for this library
     */
    @Getter
    private static final VersionDetails currentVersion = new VersionDetails(0, 9, 2);

    @Getter
    private static ApiBuilder instance = new ApiBuilder();

    @Getter
    private String apiKey = null;

    public static ApiBuilder createInstance(String apiKey) {
        if (instance.getApiKey() == null) {
            instance.apiKey = apiKey;
        } else {
            throw new IllegalStateException("You can't set your api key more than once.");
        }
        return instance;
    }

    public static ApiBuilder createInstance(String apiKey, boolean ignoreError) {
        if (ignoreError) {
            try {
                return createInstance(apiKey);
            } catch (Exception e) {
                LOG.warn("api key was already set. Ignoring exception", e);
                return instance;
            }
        } else {
            return createInstance(apiKey);
        }
    }

    public static String getApiKeyAsParam() {
        if (instance.getApiKey() == null) {
            throw new IllegalStateException("The api key must not be null");
        }
        return "?application_id=" + instance.getApiKey();
    }
}
