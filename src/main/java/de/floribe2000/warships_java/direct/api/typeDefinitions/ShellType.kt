package de.floribe2000.warships_java.direct.api.typeDefinitions

import com.google.gson.annotations.SerializedName

enum class ShellType {
    @SerializedName("HE")
    HE,

    @SerializedName("AP")
    AP
}