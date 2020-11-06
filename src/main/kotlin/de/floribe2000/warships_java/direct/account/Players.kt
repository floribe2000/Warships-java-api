package de.floribe2000.warships_java.direct.account

import com.google.gson.annotations.SerializedName
import de.floribe2000.warships_java.direct.api.*
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status

/**
 * A representation of the results of the player search result from [/wows/account/list/](https://api.worldofwarships.eu/wows/account/list/).
 * This class holds all data returned from this request to allow easy processing of the information.
 *
 * @author floribe2000
 */
class Players(
        /**
         * The response status from the api
         */
        var status: Status = Status.ERROR,

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

        /**
         * A list that contains all player details the api returned
         */
        var data: List<PlayerElement>? = null,
) : IApiResponse {

    /**
     * A class that represents a single player element from the api response.
     */
    class PlayerElement(
            /**
             * The nickname of the player
             */
            val nickname: String? = null,

            /**
             * The account id of the player
             */
            @SerializedName("account_id")
            val accountId: Long = 0,
    )

    override fun toString(): String {
        return IRequestAction.GSON.toJson(this)
    }
}