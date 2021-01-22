package de.floribe2000.warships_java.direct.api.typeDefinitions

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * An enum containing all available nations of the game.
 */
@Suppress("unused")
@Serializable
enum class Nation {
    @SerializedName("usa")
    @SerialName("usa")
    USA,

    @SerializedName("japan")
    @SerialName("japan")
    JAPAN,

    @SerializedName("germany")
    @SerialName("germany")
    GERMANY,

    @SerializedName("ussr")
    @SerialName("ussr")
    USSR,

    @SerializedName("uk")
    @SerialName("uk")
    UK,

    @SerializedName("france")
    @SerialName("france")
    FRANCE,

    @SerializedName("italy")
    @SerialName("italy")
    ITALY,

    @SerializedName("pan_asia")
    @SerialName("pan_asia")
    PAN_ASIA,

    @SerializedName("europe")
    @SerialName("europe")
    EUROPE,

    @SerializedName("commonwealth")
    @SerialName("commonwealth")
    COMMONWEALTH,

    @SerializedName("pan_america")
    @SerialName("pan_america")
    PAN_AMERICA
}