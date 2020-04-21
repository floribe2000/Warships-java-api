package de.floribe2000.warships_java.direct.api.typeDefinitions;

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
