package de.floribe2000.warships_java.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representation of all current API regions (RU, EU, NA, ASIA) containing the base URLs for API requests
 */
@AllArgsConstructor
public enum Region {

    RU("https://api.worldofwarships.ru", "ru"),
    EU("https://api.worldofwarships.eu", "eu"),
    NA("https://api.worldofwarships.com", "com"),
    ASIA("https://api.worldofwarships.asia", "asia");

    @Getter
    private String baseURL;

    @Getter
    private String code;

    public static Region parseRegion(String region) {
        try {
            return Region.valueOf(region.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
