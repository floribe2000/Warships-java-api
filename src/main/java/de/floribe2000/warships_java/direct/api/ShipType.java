package de.floribe2000.warships_java.direct.api;

import com.google.gson.annotations.SerializedName;

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

    ShipType(String typeName) {
        this.typeName = typeName;
    }

    private String typeName;


    @Override
    public String toString() {
        return this.typeName;
    }
}
