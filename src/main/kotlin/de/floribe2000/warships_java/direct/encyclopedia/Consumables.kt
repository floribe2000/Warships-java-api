package de.floribe2000.warships_java.direct.encyclopedia

import com.google.gson.annotations.SerializedName
import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.typeDefinitions.ConsumableType
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A representation of the api response for a [ConsumablesRequest].
 *
 * @author floribe2000
 * @since 0.2.12
 */
@Serializable
data class Consumables(
    override val status: Status = Status.ERROR,

    /**
     * Details about errors in case of a failed request.
     *
     * Field is null if no errors occurred during the request!
     */
    override val error: ErrorContainer? = null,

    override val meta: Meta = Meta(),

    val data: Map<String, Consumable> = mapOf(),
) : IApiResponse {

    @Serializable
    data class Consumable(
        val profile: Map<String, EntryStats>? = null,

        val name: String? = null,

        @SerializedName("price_gold")
        @SerialName("price_gold")
        val doubloonPrice: Int = 0,

        val image: String? = null,

        @SerializedName("consumable_id")
        @SerialName("consumable_id")
        val consumableId: Long = 0,

        @SerializedName("price_credit")
        @SerialName("price_credit")
        val creditPrice: Int = 0,

        val type: ConsumableType? = null,

        val description: String? = null,
    ) {
        @Serializable
        data class EntryStats(
            val description: String? = null,
            val value: Double = 0.0,
        )

        override fun toString(): String {
            return GSON.toJson(this)
        }
    }

    override fun toString(): String {
        return GSON.toJson(this)
    }
}