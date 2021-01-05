package de.floribe2000.warships_java.direct.api.typeDefinitions

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ShellType {
    @SerializedName("HE")
    @SerialName("HE")
    HE,

    @SerializedName("AP")
    @SerialName("AP")
    AP,
}