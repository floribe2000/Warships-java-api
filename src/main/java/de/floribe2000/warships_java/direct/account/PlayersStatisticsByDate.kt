package de.floribe2000.warships_java.direct.account

import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status

/**
 * A representation of the data retrieved from the api.
 *
 * Contains data of a player, split into up to 10 dates
 *
 * @author floribe2000
 */
class PlayersStatisticsByDate : IApiResponse {
    /**
     * The api response status
     */
    val status = Status.ERROR

    /**
     * Details about errors in case of a failed request.
     *
     * Field is null if no errors occurred during the request!
     */
    override val error: ErrorContainer? = null

    /**
     * The meta object of this api response
     */
    val meta: Meta? = null

    /**
     * A map containing details for the player
     */
    val data: Map<String, PlayerEntry>? = null

    /**
     * A representation of a single player entry
     */
    class PlayerEntry {
        /**
         * A Day object containing stats for the random battles mode
         */
        val pvp: Map<String, Day>? = null

        /**
         * A Day object containing stats for the coop battles mode
         */
        val pve: Map<String, Day>? = null

        /**
         * A representation of the stats for a requested day
         */
        class Day {
            val capture_points = 0
            val account_id: Long = 0
            val max_xp = 0
            val wins = 0
            val planes_killed = 0
            val battles = 0
            val damage_dealt = 0
            val battle_type: String? = null
            val date: String? = null
            val xp = 0
            val frags = 0
            val survived_battles = 0
            val dropped_capture_points = 0

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