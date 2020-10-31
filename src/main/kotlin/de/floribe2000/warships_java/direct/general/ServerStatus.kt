package de.floribe2000.warships_java.direct.general

import com.google.gson.annotations.SerializedName
import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status

/**
 * The api response for a server status request.
 *
 * @author floribe2000
 */
class ServerStatus : IApiResponse {

    val status = Status.ERROR

    /**
     * Details about errors in case of a failed request.
     *
     * Field is null if no errors occurred during the request!
     */
    override val error: ErrorContainer? = null

    /**
     * A list containing the details for all available servers.
     *
     * Possible entries are wot, wows and wotb
     */
    val data: Map<String, List<ServerEntry>>? = null

    class ServerEntry {
        @SerializedName("players_online")
        val playersOnline = 0

        val server: String? = null
    }

    override fun toString(): String {
        return GSON.toJson(this)
    }
}