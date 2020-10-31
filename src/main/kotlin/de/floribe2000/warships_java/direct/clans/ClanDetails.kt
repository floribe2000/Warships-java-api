package de.floribe2000.warships_java.direct.clans

import com.google.gson.annotations.SerializedName
import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status

class ClanDetails : IApiResponse {
    val status = Status.ERROR

    /**
     * Details about errors in case of a failed request.
     *
     * Field is null if no errors occurred during the request!
     */
    override val error: ErrorContainer? = null

    val meta: Meta? = null

    val data: Map<String, ClanEntry>? = null

    class ClanEntry {
        @SerializedName("members_count")
        val membersCount = 0

        val name: String? = null

        @SerializedName("creator_name")
        val creatorName: String? = null

        @SerializedName("created_at")
        val createdAt: Long = 0

        val tag: String? = null

        @SerializedName("updated_at")
        val updatedAt: Long = 0

        @SerializedName("leader_name")
        val leaderName: String? = null

        @SerializedName("members_ids")
        val membersIds: List<Long>? = null

        @SerializedName("creator_id")
        val creatorId: Long = 0

        @SerializedName("clan_id")
        val clanId: Long = 0

        val members: Map<String, ClanMember> = HashMap()

        @SerializedName("is_clan_disbanded")
        val isClanDisbanded: Boolean = false

        @SerializedName("old_name")
        val oldName: String? = null

        @SerializedName("renamed_at")
        val renamedAt: Long = 0

        @SerializedName("old_tag")
        val oldTag: String? = null

        @SerializedName("leader_id")
        val leaderId: Long = 0

        val description: String? = null

        class ClanMember {
            val role: ClanRole = ClanRole.LINE_OFFICER

            @SerializedName("joined_at")
            val joinedAt: Long = 0

            @SerializedName("account_id")
            val accountId: Long = 0

            @SerializedName("account_name")
            val accountName: String = ""
        }
    }

    override fun toString(): String {
        return GSON.toJson(this)
    }
}