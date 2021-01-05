package de.floribe2000.warships_java.direct.clans

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * An enum class representing the clan roles available in the game.
 */
@Serializable
@Suppress("UNUSED")
enum class ClanRole {
    @SerializedName("officer")
    @SerialName("officer")
    OFFICER,

    @SerializedName("commissioned_officer")
    @SerialName("commissioned_officer")
    COMMISSIONED_OFFICER,

    @SerializedName("private")
    @SerialName("private")
    LINE_OFFICER,

    @SerializedName("recruitment_officer")
    @SerialName("recruitment_officer")
    RECRUITMENT_OFFICER,

    @SerializedName("executive_officer")
    @SerialName("executive_officer")
    DEPUTY_COMMANDER,

    @SerializedName("commander")
    @SerialName("commander")
    COMMANDER,
}