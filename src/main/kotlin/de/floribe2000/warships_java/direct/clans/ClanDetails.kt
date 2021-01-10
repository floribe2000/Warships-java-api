package de.floribe2000.warships_java.direct.clans

import com.google.gson.annotations.SerializedName
import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClanDetails(
    override val status: Status = Status.ERROR,

    /**
     * Details about errors in case of a failed request.
     *
     * Field is null if no errors occurred during the request!
     */
    override val error: ErrorContainer? = null,

    override val meta: Meta = Meta(),

    val data: Map<String, ClanEntry> = mapOf(),

    ) : IApiResponse {

    @Serializable
    data class ClanEntry(
        @SerializedName("members_count")
        @SerialName("members_count")
        val membersCount: Int,

        val name: String,

        @SerializedName("creator_name")
        @SerialName("creator_name")
        val creatorName: String? = null,

        @SerializedName("created_at")
        @SerialName("created_at")
        val createdAt: Long = 0,

        val tag: String,

        @SerializedName("updated_at")
        @SerialName("updated_at")
        val updatedAt: Long = 0,

        @SerializedName("leader_name")
        @SerialName("leader_name")
        val leaderName: String? = null,

        @SerializedName("members_ids")
        @SerialName("members_ids")
        val membersIds: List<Long> = listOf(),

        @SerializedName("creator_id")
        @SerialName("creator_id")
        val creatorId: Long = 0,

        @SerializedName("clan_id")
        @SerialName("clan_id")
        val clanId: Long = 0,

        val members: Map<String, ClanMember> = mapOf(),

        @SerializedName("is_clan_disbanded")
        @SerialName("is_clan_disbanded")
        val isClanDisbanded: Boolean = false,

        @SerializedName("old_name")
        @SerialName("old_name")
        val oldName: String? = null,

        @SerializedName("renamed_at")
        @SerialName("renamed_at")
        val renamedAt: Long? = null,

        @SerializedName("old_tag")
        @SerialName("old_tag")
        val oldTag: String? = null,

        @SerializedName("leader_id")
        @SerialName("leader_id")
        val leaderId: Long = 0,

        val description: String? = null,
    ) {
        @Serializable
        data class ClanMember(
            val role: ClanRole = ClanRole.LINE_OFFICER,

            @SerializedName("joined_at")
            @SerialName("joined_at")
            val joinedAt: Long = 0,

            @SerializedName("account_id")
            @SerialName("account_id")
            val accountId: Long = 0,

            @SerializedName("account_name")
            @SerialName("account_name")
            val accountName: String = "",
        )
    }

    override fun toString(): String {
        return GSON.toJson(this)
    }
}