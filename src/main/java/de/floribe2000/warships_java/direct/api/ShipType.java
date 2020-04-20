package de.floribe2000.warships_java.direct.api;

import com.google.gson.annotations.SerializedName;

public enum ShipType {
    @SerializedName("AirCarrier")
    AIRCRAFT_CARRIER,
    @SerializedName("Destroyer")
    DESTROYER,
    @SerializedName("Cruiser")
    CRUISER,
    @SerializedName("Battleship")
    BATTLESHIP,
    @SerializedName("Submarine")
    SUBMARINE
}
