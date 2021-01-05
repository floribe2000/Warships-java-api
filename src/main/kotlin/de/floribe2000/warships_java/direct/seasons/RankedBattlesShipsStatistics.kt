package de.floribe2000.warships_java.direct.seasons

import com.google.gson.annotations.SerializedName
import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.stats.RankedStatsContainer
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status
import kotlinx.serialization.Serializable

/**
 * A representation of the api result of ranked battles ships statistics. This class holds all data
 * returned by this request to allow easy access to this information
 *
 * @author SirLefti
 */
@Deprecated("Ranked Battle statistics have been changed by wargaming.")
data class RankedBattlesShipsStatistics(
    /**
     * Response status
     */
    override val status: Status = Status.ERROR,

    /**
     * Details about errors in case of a failed request.
     *
     * Field is null if no errors occurred during the request!
     */
    override val error: ErrorContainer? = null,

    /**
     * Response meta data
     */
    override val meta: Meta = Meta(),

    /**
     * Map containing request objects
     */
    val data: Map<Long, List<ShipElement>> = mapOf(),
) : IApiResponse {

    data class ShipElement(
        /**
         * players accountId
         */
        @SerializedName("account_id")
        val accountId: Long = 0,

        /**
         * ship id
         */
        @SerializedName("ship_id")
        val shipId: Long = 0,

        /**
         * Map containing request objects
         */
        val seasons: Map<Int, StatsContainer> = mapOf(),
    ) {

        data class StatsContainer(
            /**
             * Info about season results
             */
            @SerializedName("rank_info")
            val generalInfo: SeasonInfo? = null,

            @SerializedName("rank_div3")
            val rankedDiv3: RankedStatsContainer? = null,

            @SerializedName("rank_solo")
            val rankedSolo: RankedStatsContainer? = null,

            @SerializedName("rank_div2")
            val rankedDiv2: RankedStatsContainer? = null,
        )

        data class SeasonInfo(
            /**
             * max reached rank
             */
            @SerializedName("max_rank")
            val maxRank: Int = 0,

            /**
             * start rank
             */
            @SerializedName("start_rank")
            val startRank: Int = 0,

            /**
             * stars
             */
            val stars: Int = 0,

            /**
             * current/final rank
             */
            val rank: Int = 0,

            /**
             * stage
             */
            val stage: Int = 0,
        )
    }
}