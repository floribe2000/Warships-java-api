package de.floribe2000.warships_java.direct.clans

import com.google.gson.annotations.SerializedName
import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status
import kotlinx.serialization.SerialName

data class Clans(
    override val status: Status = Status.ERROR,

    /**
     * Details about errors in case of a failed request.
     *
     * Field is null if no errors occurred during the request!
     */
    override val error: ErrorContainer? = null,

    override val meta: Meta = Meta(),

    val data: List<ClanDetails> = listOf(),
) : IApiResponse {


    data class ClanDetails(
        @SerializedName("members_count")
        @SerialName("members_count")
        val membersCount: Int,

        @SerializedName("created_at")
        @SerialName("created_at")
        val createdAt: Long,

        @SerializedName("clan_id")
        @SerialName("clan_id")
        val clanId: Long,

        val tag: String,

        val name: String,
    )

    override fun toString(): String {
        return GSON.toJson(this)
    }
}