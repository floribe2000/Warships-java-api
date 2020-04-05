package de.floribe2000.warships_java.api;

import com.google.gson.annotations.SerializedName;

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
