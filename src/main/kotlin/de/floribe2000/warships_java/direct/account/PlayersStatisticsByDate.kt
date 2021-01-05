package de.floribe2000.warships_java.direct.account

import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status
import kotlinx.serialization.Serializable

/**
 * A representation of the data retrieved from the api.
 *
 * Contains data of a player, split into up to 10 dates
 *
 * @author floribe2000
 */
@Serializable
data class PlayersStatisticsByDate(
    /**
     * The api response status
     */
    override val status: Status = Status.ERROR,

    /**
     * Details about errors in case of a failed request.
     *
     * Field is null if no errors occurred during the request!
     */
    override val error: ErrorContainer? = null,

    /**
     * The meta object of this api response
     */
    override val meta: Meta = Meta(),

    /**
     * A map containing details for the player
     */
    val data: Map<String, PlayerEntry> = mapOf(),

    ) : IApiResponse {

    /**
     * A representation of a single player entry
     */
    @Serializable
    data class PlayerEntry(
        /**
         * A Day object containing stats for the random battles mode
         */
        val pvp: Map<String, Day> = mapOf(),

        /**
         * A Day object containing stats for the coop battles mode
         */
        val pve: Map<String, Day>? = null,
    ) {
        /**
         * A representation of the stats for a requested day
         */
        @Serializable
        data class Day(
            val capture_points: Int = 0,
            val account_id: Long = 0,
            val max_xp: Int = 0,
            val wins: Int = 0,
            val planes_killed: Int = 0,
            val battles: Int = 0,
            val damage_dealt: Long = 0,
            val battle_type: String? = null,
            val date: String? = null,
            val xp: Long = 0,
            val frags: Int = 0,
            val survived_battles: Int = 0,
            val dropped_capture_points: Int = 0,
        ) {
            override fun toString(): String {
                return GSON.toJson(this)
            }
        }

        override fun toString(): String {
            return GSON.toJson(this)
        }
    }

    override fun toString(): String {
        return GSON.toJson(this)
    }
}