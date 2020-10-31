package de.floribe2000.warships_java.direct.seasons

import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status

/**
 * A representation of the api result of ranked battles seasons.
 * This class holds all data returned by this request to allow easy access to this information
 *
 * @author SirLefti
 */
class RankedBattlesSeasons : IApiResponse {
    /**
     * Response status
     */
    val status: Status = Status.ERROR

    /**
     * Details about errors in case of a failed request.
     *
     * Field is null if no errors occurred during the request!
     */
    override val error: ErrorContainer? = null

    /**
     * Response meta data
     */
    val meta: Meta? = null

    /**
     * Map containing request objects
     */
    val data: Map<Int, SeasonElement>? = null

    /**
     * A class representing a single season returned by the api
     */
    class SeasonElement {
        /**
         * required account tier
         */
        val account_tier = 0

        /**
         * max battle tier
         */
        val max_ship_tier = 0

        /**
         * season start timestamp
         */
        val start_at: Long = 0

        /**
         * seasons name
         */
        val season_name: String? = null

        /**
         * season end timestamp
         */
        val finish_at: Long = 0

        /**
         * seasons id
         */
        val season_id = 0

        /**
         * min battle tier
         */
        val min_ship_tier = 0

        /**
         * parent season
         */
        val parent_season_id = 0

        /**
         * start rank
         */
        val start_rank = 0

        /**
         * close timestamp
         */
        val close_at: Long = 0

        /**
         * rank badges image URLs
         */
        val images: Map<Int, ImageElement>? = null

        class ImageElement {
            /**
             * URL for rank insignia image
             */
            val insignia: String? = null

            /**
             * URL for rank background image
             */
            val background: String? = null
        }
    }

    override fun toString(): String {
        return GSON.toJson(this)
    }
}