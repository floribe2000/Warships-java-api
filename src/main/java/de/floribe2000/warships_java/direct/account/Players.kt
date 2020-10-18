package de.floribe2000.warships_java.direct.account

import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.account.Players.PlayerElement
import de.floribe2000.warships_java.direct.api.IRequestAction
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status

/**
 * A representation of the results of the player search result from [/wows/account/list/](https://api.worldofwarships.eu/wows/account/list/).
 * This class holds all data returned from this request to allow easy processing of the information.
 *
 * @author floribe2000
 */
class Players : IApiResponse {
    /**
     * The response status from the api
     */
    var status = Status.ERROR

    /**
     * Details about errors in case of a failed request.
     *
     * Field is null if no errors occurred during the request!
     */
    private var error: ErrorContainer? = null

    /**
     * The meta object of the api response
     */
    var meta: Meta? = null

    /**
     * A list that contains all player details the api returned
     */
    var data: List<PlayerElement>? = null

    override fun getError(): ErrorContainer? {
        return error
    }

    /**
     * A class that represents a single player element from the api response.
     */
    class PlayerElement {
        /**
         * The nickname of the player
         */
        var nickname: String? = null

        /**
         * The account id of the player
         */
        var account_id: Long = 0
    }

    override fun toString(): String {
        return IRequestAction.GSON.toJson(this)
    }
}