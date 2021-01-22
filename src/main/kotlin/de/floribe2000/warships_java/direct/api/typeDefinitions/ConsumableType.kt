package de.floribe2000.warships_java.direct.api.typeDefinitions

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A list of entry types for the consumable encyclopedia request and response.
 */
@Suppress("unused")
@Serializable
enum class ConsumableType(private val key: String) {
    @SerializedName("Camouflage")
    @SerialName("Camouflage")
    CAMOUFLAGE("Camouflage"),

    @SerializedName("Flags")
    @SerialName("Flags")
    FLAGS("Flags"),

    @SerializedName("Permoflage")
    @SerialName("Permoflage")
    PERMOFLAGE("Permoflage"),

    @SerializedName("Modernization")
    @SerialName("Modernization")
    MODERNIZATION("Modernization"),

    @SerializedName("Skin")
    @SerialName("Skin")
    SKIN("Skin");

    override fun toString(): String {
        return "&type=$key"
    }
}