package de.floribe2000.warships_java.direct.api.typeDefinitions;

import com.google.gson.annotations.SerializedName;

/**
 * A list of entry types for the consumable encyclopedia request and response.
 */
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

    private ConsumableType(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "&type=" + key;
    }
}
