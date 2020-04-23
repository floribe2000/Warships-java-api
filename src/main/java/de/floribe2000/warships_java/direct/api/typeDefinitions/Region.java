package de.floribe2000.warships_java.direct.api.typeDefinitions;

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

    /**
     * The base url for this region
     */
    @Getter
    private final String baseURL;

    /**
     * The domain for this region
     */
    @Getter
    private final String code;

    /**
     * Parses the region from a string.
     *
     * @param region the string of the region
     * @return the region for the string or null if there are no matches
     */
    public static Region parseRegion(String region) {
        try {
            return Region.valueOf(region.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * A utility method to determine the api region based on the range of the provided id.
     *
     * @param id the id to check
     * @return the region for the provided id
     */
    public static Region fromRange(int id) {
        if (id >= 0 && id < 500_000_000) {
            return RU;
        } else if (id >= 500_000_000 && id < 1_000_000_000) {
            return EU;
        } else if (id >= 1_000_000_000 && id < 2_000_000_000) {
            return NA;
        } else {
            return ASIA;
        }
    }
}
