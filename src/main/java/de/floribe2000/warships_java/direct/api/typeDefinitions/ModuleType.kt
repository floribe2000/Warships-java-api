package de.floribe2000.warships_java.direct.api.typeDefinitions;

import com.google.gson.annotations.SerializedName;

/**
 * An enum that contains the different module types of ships.
 */
public enum ModuleType {
    @SerializedName("Hull")
    HULL,
    @SerializedName("Torpedoes")
    TORPEDOES,
    @SerializedName("Engine")
    ENGINE,
    @SerializedName("Artillery")
    ARTILLERY,
    @SerializedName("Suo")
    SUO
}
