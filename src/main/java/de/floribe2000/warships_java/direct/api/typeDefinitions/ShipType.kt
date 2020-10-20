package de.floribe2000.warships_java.direct.api.typeDefinitions;

import com.google.gson.annotations.SerializedName;

/**
 * All ship types that are available in the game.
 */
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

    private ShipType(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return this.typeName;
    }
}
