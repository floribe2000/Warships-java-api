package de.floribe2000.warships_java.direct.encyclopedia

import com.google.gson.annotations.SerializedName
import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.typeDefinitions.ConsumableType
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status

/**
 * A representation of the api response for a [ConsumablesRequest].
 *
 * @author floribe2000
 * @since 0.2.12
 */
class Consumables : IApiResponse {

    val status = Status.ERROR

    /**
     * Details about errors in case of a failed request.
     *
     * Field is null if no errors occurred during the request!
     */
    override val error: ErrorContainer? = null

    val meta: Meta? = null

    val data: Map<String, Consumable>? = null

    class Consumable {
        val profile: Map<String, EntryStats>? = null

        class EntryStats {
            val description: String? = null
            val value = 0.0
        }

        val name: String? = null

        @SerializedName("price_gold")
        val doubloonPrice = 0

        val image: String? = null

        @SerializedName("consumable_id")
        val consumableId: Long = 0

        @SerializedName("price_credit")
        val creditPrice = 0

        val type: ConsumableType? = null

        val description: String? = null

        override fun toString(): String {
            return GSON.toJson(this)
        }
    }

    override fun toString(): String {
        return GSON.toJson(this)
    }
}