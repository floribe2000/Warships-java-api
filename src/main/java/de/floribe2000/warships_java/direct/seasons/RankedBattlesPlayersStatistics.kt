package de.floribe2000.warships_java.direct.seasons

import com.google.gson.annotations.SerializedName
import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status
import de.floribe2000.warships_java.direct.seasons.RankedBattlesShipsStatistics.ShipElement.StatsContainer

/**
 * A representation of the api result of ranked battles player statistics. This class holds all data
 * returned by this request to allow easy access to this information
 *
 * @author SirLefti
 */
class RankedBattlesPlayersStatistics : IApiResponse {
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
    val data: Map<Int, PlayersElement>? = null

    // TODO remove duplicate elements, see RankedBattlesShipsStatistics.kt
    class PlayersElement {
        /**
         * players accountId
         */
        @SerializedName("account_id")
        val accountId: Long = 0

        /**
         * Map containing request objects
         */
        val seasons: Map<Int, StatsContainer>? = null
    }
}