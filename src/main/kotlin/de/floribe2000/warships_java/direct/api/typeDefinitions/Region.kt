package de.floribe2000.warships_java.direct.api.typeDefinitions

/**
 * Enum representation of all current API regions (RU, EU, NA, ASIA) containing the base URLs for API requests
 */
enum class Region(
        /**
         * The base url for this region.
         */
        val baseURL: String,
        /**
         * The domain for this region.
         */
        val code: String,
        /**
         * The start of the id range of the region.
         */
        val start: Long,

        /**
         * The end of the id range of the region.
         */
        val end: Long,
) {
    RU("https://api.worldofwarships.ru", "ru", 1, 499999999),
    EU("https://api.worldofwarships.eu", "eu", 500000000, 999999999),
    NA("https://api.worldofwarships.com", "com", 1000000000, 1999999999),
    ASIA("https://api.worldofwarships.asia", "asia", 2000000000, 2999999999);

    val range: LongRange
        get() = start..end

    companion object {
        /**
         * Parses the region from a string.
         *
         * @param region the string of the region
         * @return the region for the string or null if there are no matches
         */
        fun parseRegion(region: String): Region {
            return valueOf(region.trim().toUpperCase())
        }

        /**
         * A utility method to determine the api region based on the range of the provided id.
         *
         * @param id the id to check
         * @return the region for the provided id
         */
        fun fromRange(id: Long): Region {
            var result: Region = ASIA
            for (region in values()) {
                if (id in region.start..region.end) {
                    result = region
                    break
                }
            }
            return result
        }
    }
}