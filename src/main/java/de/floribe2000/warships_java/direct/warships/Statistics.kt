package de.floribe2000.warships_java.direct.warships

import com.google.gson.annotations.SerializedName
import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.stats.OperationStatsContainer
import de.floribe2000.warships_java.direct.api.stats.ShipStatsContainer
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status

class Statistics : IApiResponse {

    val status = Status.ERROR

    /**
     * Details about errors in case of a failed request.
     *
     * Field is null if no errors occurred during the request!
     */
    override val error: ErrorContainer? = null
    val meta: Meta? = null
    val data: Map<Long, List<ShipEntry>>? = null

    class ShipEntry {

        val pvp: ShipStatsContainer? = null

        val pve: ShipStatsContainer? = null

        val pve_div2: ShipStatsContainer? = null

        val pve_div3: ShipStatsContainer? = null

        val pve_solo: ShipStatsContainer? = null

        val pvp_div2: ShipStatsContainer? = null

        val pvp_div3: ShipStatsContainer? = null

        val pvp_solo: ShipStatsContainer? = null

        val rank_div2: ShipStatsContainer? = null

        val rank_div3: ShipStatsContainer? = null

        val rank_solo: ShipStatsContainer? = null

        val oper_div: OperationStatsContainer? = null

        val oper_div_hard: OperationStatsContainer? = null

        val oper_solo: OperationStatsContainer? = null

        @SerializedName("last_battle_time")
        val lastBattleTime: Long = 0

        @SerializedName("account_id")
        val accountId: Long = 0

        val distance = 0

        @SerializedName("updated_at")
        val updatedAt: Long = 0

        val battles = 0

        @SerializedName("ship_id")
        val shipId: Long = 0

        override fun toString(): String {
            return GSON.toJson(this)
        }
    }

    override fun toString(): String {
        return GSON.toJson(this)
    }
}