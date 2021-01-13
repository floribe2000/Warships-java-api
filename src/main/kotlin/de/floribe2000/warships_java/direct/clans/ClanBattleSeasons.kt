package de.floribe2000.warships_java.direct.clans

import com.google.gson.annotations.SerializedName
import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status
import de.floribe2000.warships_java.direct.api.typeDefinitions.Tier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClanBattleSeasons(
    override val status: Status = Status.ERROR,
    override val error: ErrorContainer? = null,
    override val meta: Meta = Meta(),
    val data: Map<Int, SeasonDetails> = mapOf(),
) : IApiResponse {

    @Serializable
    data class SeasonDetails(
        val leagues: List<LeagueDetails> = listOf(),
        val name: String = "",

        @SerializedName("ship_tier_max")
        @SerialName("ship_tier_max")
        val maxTier: Tier = Tier.I,

        @SerializedName("ship_tier_min")
        @SerialName("ship_tier_min")
        val minTier: Tier = Tier.I,

        @SerializedName("start_time")
        @SerialName("start_time")
        val startTime: Long = 0,

        @SerializedName("finish_time")
        @SerialName("finish_time")
        val finishTime: Long = 0,

        @SerializedName("division_points")
        @SerialName("division_points")
        val divisionPoints: Int = 0,

        @SerializedName("season_id")
        @SerialName("season_id")
        val seasonId: Int = -1,
    )

    @Serializable
    data class LeagueDetails(
        val color: String = "#000000",
        val name: String = "",
        val icon: String = "",
    )
}