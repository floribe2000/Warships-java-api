package de.floribe2000.warships_java.direct.api;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;

/**
 * A list of entry types for the consumable encyclopedia request and response.
 */
@AllArgsConstructor
public enum ConsumableType {
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

    private final String key;

    @Override
    public String toString() {
        return "&type=" + key;
    }
}
