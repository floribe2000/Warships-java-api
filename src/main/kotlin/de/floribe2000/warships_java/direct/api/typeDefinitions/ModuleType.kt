package de.floribe2000.warships_java.direct.api.typeDefinitions

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * An enum that contains the different module types of ships.
 */
@Suppress("unused")
@Serializable
enum class ModuleType {
    @SerializedName("Hull")
    @SerialName("Hull")
    HULL,

    @SerializedName("Torpedoes")
    @SerialName("Torpedoes")
    TORPEDOES,

    @SerializedName("Engine")
    @SerialName("Engine")
    ENGINE,

    @SerializedName("Artillery")
    @SerialName("Artillery")
    ARTILLERY,

    @SerializedName("Suo")
    @SerialName("Suo")
    SUO,

    @SerializedName("FlightControl")
    @SerialName("FlightControl")
    FLIGHT_CONTROL,

    @SerializedName("DiveBomber")
    @SerialName("DiveBomber")
    DIVE_BOMBER,

    @SerializedName("Fighter")
    @SerialName("Fighter")
    FIGHTER,

    @SerializedName("TorpedoBomber")
    @SerialName("TorpedoBomber")
    TORPEDO_BOMBER,
}