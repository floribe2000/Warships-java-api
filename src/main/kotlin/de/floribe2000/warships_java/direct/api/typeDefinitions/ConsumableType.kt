package de.floribe2000.warships_java.direct.api.typeDefinitions

import com.google.gson.annotations.SerializedName

/**
 * A list of entry types for the consumable encyclopedia request and response.
 */
enum class ConsumableType(private val key: String) {
    @SerializedName("Camouflage")
    CAMOUFLAGE("Camouflage"),

    @SerializedName("Flags")
    FLAGS("Flags"),

    @SerializedName("Permoflage")
    PERMOFLAGE("Permoflage"),

    @SerializedName("Modernization")
    MODERNIZATION("Modernization"),

    @SerializedName("Skin")
    SKIN("Skin");

    override fun toString(): String {
        return "&type=$key"
    }
}