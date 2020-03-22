package de.floribe2000.warships_java.api;

import lombok.Getter;

public class ApiBuilder {

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

    public static String getApiKeyAsParam() {
        if (instance.getApiKey() == null) {
            throw new IllegalStateException("The api key must not be null");
        }
        return "?application_id=" + instance.getApiKey();
    }
}
