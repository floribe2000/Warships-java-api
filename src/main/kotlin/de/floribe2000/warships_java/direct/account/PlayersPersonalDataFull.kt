package de.floribe2000.warships_java.direct.account

import com.google.gson.annotations.SerializedName
import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.stats.OperationStatsContainer
import de.floribe2000.warships_java.direct.api.stats.PlayerStatsContainer
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status

/**
 * A representation of a full data set from the api. Contains all available fields for player and
 * player pvp stats.
 */
class PlayersPersonalDataFull(
        /**
         * The response status from the api
         */
        val status: Status = Status.ERROR,

        /**
         * Details about errors in case of a failed request.
         *
         * Field is null if no errors occurred during the request!
         */
        override val error: ErrorContainer? = null,

        /**
         * The meta object of the api response
         */
        val meta: Meta? = null,

        val data: Map<String, PlayerDetails>? = null,
) : IApiResponse {

    /**
     * A representation of individual player statistics.
     */
    class PlayerDetails {
        @SerializedName("last_battle_time")
        val lastBattleTime = 0

        @SerializedName("account_id")
        val accountId: Long = 0

        @SerializedName("leveling_tier")
        val levelingTier = 0

        @SerializedName("created_at")
        val createdAt = 0

        @SerializedName("hidden_profile")
        val idHiddenProfile = false

        @SerializedName("logout_at")
        val logoutAt = 0

        val statistics: Statistics? = null

        /**
         * A representation of the player's battle statistics
         */
        class Statistics {
            val distance = 0
            val battles = 0
            val pvp: PlayerStatsContainer? = null
            val pve: PlayerStatsContainer? = null
            val pvp_div2: PlayerStatsContainer? = null
            val pvp_div3: PlayerStatsContainer? = null
            val pve_div2: PlayerStatsContainer? = null
            val pve_div3: PlayerStatsContainer? = null
            val rank_solo: PlayerStatsContainer? = null
            val rank_div2: PlayerStatsContainer? = null
            val rank_div3: PlayerStatsContainer? = null
            val oper_solo: OperationStatsContainer? = null
            val oper_div: OperationStatsContainer? = null
            val oper_div_hard: OperationStatsContainer? = null
        }

        val nickname: String? = null

        @SerializedName("stats_updated_at")
        val statsUpdatedAt: Long = 0
    }

    override fun toString(): String {
        return GSON.toJson(this)
    }
}