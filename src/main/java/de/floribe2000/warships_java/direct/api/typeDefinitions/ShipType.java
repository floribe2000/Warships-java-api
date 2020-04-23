package de.floribe2000.warships_java.direct.api.typeDefinitions;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;

/**
 * All ship types that are available in the game.
 */
@AllArgsConstructor
public enum ShipType {
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

    /**
     * The spelled name of the type
     */
    private final String typeName;

    @Override
    public String toString() {
        return this.typeName;
    }
}
