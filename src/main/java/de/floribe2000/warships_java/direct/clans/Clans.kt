package de.floribe2000.warships_java.direct.clans

import com.google.gson.annotations.SerializedName
import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status

class Clans : IApiResponse {
    val status = Status.ERROR

    /**
     * Details about errors in case of a failed request.
     *
     * Field is null if no errors occurred during the request!
     */
    override val error: ErrorContainer? = null

    val meta: Meta? = null

    val data: List<ClanDetails>? = null

    class ClanDetails {
        @SerializedName("members_count")
        val membersCount = 0

        @SerializedName("created_at")
        val createdAt: Long = 0

        @SerializedName("clan_id")
        val clanId = 0

        val tag: String? = null

        val name: String? = null
    }

    override fun toString(): String {
        return GSON.toJson(this)
    }
}