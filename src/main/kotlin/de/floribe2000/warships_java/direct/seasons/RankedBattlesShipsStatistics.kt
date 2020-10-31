package de.floribe2000.warships_java.direct.seasons

import com.google.gson.annotations.SerializedName
import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.stats.RankedStatsContainer
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status

/**
 * A representation of the api result of ranked battles ships statistics. This class holds all data
 * returned by this request to allow easy access to this information
 *
 * @author SirLefti
 */
class RankedBattlesShipsStatistics : IApiResponse {
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
    val data: Map<Long, List<ShipElement>>? = null

    class ShipElement {
        /**
         * players accountId
         */
        @SerializedName("account_id")
        val accountId: Long = 0

        /**
         * ship id
         */
        @SerializedName("ship_id")
        val shipId: Long = 0

        /**
         * Map containing request objects
         */
        val seasons: Map<Int, StatsContainer>? = null

        class StatsContainer {
            /**
             * Info about season results
             */
            @SerializedName("rank_info")
            val generalInfo: SeasonInfo? = null

            @SerializedName("rank_div3")
            val rankedDiv3: RankedStatsContainer? = null

            @SerializedName("rank_solo")
            val rankedSolo: RankedStatsContainer? = null

            @SerializedName("rank_div2")
            val rankedDiv2: RankedStatsContainer? = null
        }

        class SeasonInfo {
            /**
             * max reached rank
             */
            @SerializedName("max_rank")
            val maxRank = 0

            /**
             * start rank
             */
            @SerializedName("start_rank")
            val startRank = 0

            /**
             * stars
             */
            val stars = 0

            /**
             * current/final rank
             */
            val rank = 0

            /**
             * stage
             */
            val stage = 0
        }
    }
}