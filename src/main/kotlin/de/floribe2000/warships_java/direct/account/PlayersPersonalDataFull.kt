package de.floribe2000.warships_java.direct.account

import com.google.gson.annotations.SerializedName
import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.stats.OperationStatsContainer
import de.floribe2000.warships_java.direct.api.stats.PlayerStatsContainer
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A representation of a full data set from the api. Contains all available fields for player and
 * player pvp stats.
 */
@Serializable
data class PlayersPersonalDataFull(
    /**
     * The response status from the api
     */
    override val status: Status = Status.ERROR,

    /**
     * Details about errors in case of a failed request.
     *
     * Field is null if no errors occurred during the request!
     */
    override val error: ErrorContainer? = null,

    /**
     * The meta object of the api response
     */
    override val meta: Meta = Meta(),

    /**
     * A map containing the data returned by the api.
     */
    val data: Map<String, PlayerDetails?> = mapOf(),

    ) : IApiResponse {

    /**
     * A representation of individual player statistics.
     */
    @Serializable
    data class PlayerDetails(
        @SerializedName("last_battle_time")
        @SerialName("last_battle_time")
        val lastBattleTime: Long = 0,

        @SerializedName("account_id")
        @SerialName("account_id")
        val accountId: Long = 0,

        @SerializedName("leveling_tier")
        @SerialName("leveling_tier")
        val levelingTier: Int? = 0,

        @SerializedName("created_at")
        @SerialName("created_at")
        val createdAt: Long = 0,

        @SerializedName("hidden_profile")
        @SerialName("hidden_profile")
        val isHiddenProfile: Boolean = false,

        @SerializedName("logout_at")
        @SerialName("logout_at")
        val logoutAt: Long = 0,

        @SerializedName("leveling_points")
        @SerialName("leveling_points")
        val serviceRecordLevel: Int? = 0,

        val statistics: Statistics? = null,

        val nickname: String? = null,

        @SerializedName("stats_updated_at")
        @SerialName("stats_updated_at")
        val statsUpdatedAt: Long = 0,

        @SerializedName("updated_at")
        @SerialName("updated_at")
        val updatedAt: Long = 0,

        val private: PrivatePlayerData? = null,

        /**
         * The karma of the player. The api will never return a result.
         */
        private val karma: Int? = null,
    ) {
        /**
         * A representation of the player's battle statistics
         */
        @Serializable
        data class Statistics(
            val distance: Int = 0,
            val battles: Int = 0,
            val pvp: PlayerStatsContainer? = null,
            val pve: PlayerStatsContainer? = null,
            val pvp_div2: PlayerStatsContainer? = null,
            val pvp_div3: PlayerStatsContainer? = null,
            val pve_div2: PlayerStatsContainer? = null,
            val pve_div3: PlayerStatsContainer? = null,
            val rank_solo: PlayerStatsContainer? = null,
            val rank_div2: PlayerStatsContainer? = null,
            val rank_div3: PlayerStatsContainer? = null,
            val oper_solo: OperationStatsContainer? = null,
            val oper_div: OperationStatsContainer? = null,
            val oper_div_hard: OperationStatsContainer? = null,
        )
    }
}