package de.floribe2000.warships_java.direct.api.typeDefinitions

import com.google.gson.annotations.SerializedName

/**
 * All ship types that are available in the game.
 */
enum class ShipType(
        /**
         * The spelled name of the type
         */
        private val typeName: String) {

    @SerializedName("AirCarrier")
    AIRCRAFT_CARRIER("AirCarrier"),

    @SerializedName("Destroyer")
    DESTROYER("Destroyer"),

    @SerializedName("Cruiser")
    CRUISER("Cruiser"),

    @SerializedName("Battleship")
    BATTLESHIP("Battleship"),

    @SerializedName("Submarine")
    SUBMARINE("Submarine");

    override fun toString(): String {
        return typeName
    }
}