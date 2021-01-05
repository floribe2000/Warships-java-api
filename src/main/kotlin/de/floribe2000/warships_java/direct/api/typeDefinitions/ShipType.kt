package de.floribe2000.warships_java.direct.api.typeDefinitions

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * All ship types that are available in the game.
 */
@Serializable
enum class ShipType(
    /**
     * The spelled name of the type
     */
    private val typeName: String,
) {

    @SerializedName("AirCarrier")
    @SerialName("AirCarrier")
    AIRCRAFT_CARRIER("AirCarrier"),

    @SerializedName("Destroyer")
    @SerialName("Destroyer")
    DESTROYER("Destroyer"),

    @SerializedName("Cruiser")
    @SerialName("Cruiser")
    CRUISER("Cruiser"),

    @SerializedName("Battleship")
    @SerialName("Battleship")
    BATTLESHIP("Battleship"),

    @SerializedName("Submarine")
    @SerialName("Submarine")
    SUBMARINE("Submarine");

    override fun toString(): String {
        return typeName
    }
}