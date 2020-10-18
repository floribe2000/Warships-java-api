package de.floribe2000.warships_java.direct.api.typeDefinitions;

/**
 * Enum representation of all current API regions (RU, EU, NA, ASIA) containing the base URLs for API requests
 */
public enum Region {

    RU("https://api.worldofwarships.ru", "ru", 1),
    EU("https://api.worldofwarships.eu", "eu", 500_000_000),
    NA("https://api.worldofwarships.com", "com", 1_000_000_000),
    ASIA("https://api.worldofwarships.asia", "asia", 2_000_000_000);

    /**
     * The base url for this region
     */
    private final String baseURL;

    /**
     * The domain for this region
     */
    private final String code;

    /**
     * The start of the id range of the region
     */
    private final int start;

    private Region(String baseURL, String code, int start) {
        this.baseURL = baseURL;
        this.code = code;
        this.start = start;
    }

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
    public static Region fromRange(long id) {
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

    public String getBaseURL() {
        return this.baseURL;
    }

    public String getCode() {
        return this.code;
    }

    public int getStart() {
        return this.start;
    }
}
