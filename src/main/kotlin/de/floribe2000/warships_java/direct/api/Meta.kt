package de.floribe2000.warships_java.direct.api

import kotlinx.serialization.Serializable

/**
 * A representation of the general meta block that is available on all api requests.
 */
@Serializable
data class Meta(
    /**
     * The number of results returned on this page
     */
    val count: Int = -1,

    /**
     * The total number of pages for this request
     */
    val page_total: Int = -1,

    /**
     * The total number of results for this request
     */
    val total: Int = -1,

    /**
     * The limit of results per page
     */
    val limit: Int = -1,

    /**
     * The current page number
     */
    val page: Int = -1,

    val hidden: List<Long>? = null,
)