package de.floribe2000.warships_java.direct.api

/**
 * A representation of the general meta block that is available on all api requests.
 */
data class Meta(
        /**
         * The number of results returned on this page
         */
        val count: Int,

        /**
         * The total number of pages for this request
         */
        val page_total: Int,

        /**
         * The total number of results for this request
         */
        val total: Int,

        /**
         * The limit of results per page
         */
        val limit: Int,

        /**
         * The current page number
         */
        val page: Int,
)