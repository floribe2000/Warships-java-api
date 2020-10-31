package de.floribe2000.warships_java.direct.clans

import com.google.gson.annotations.SerializedName

enum class ClanRole {
    @SerializedName("officer")
    OFFICER,

    @SerializedName("commissioned_officer")
    COMMISSIONED_OFFICER,

    @SerializedName("private")
    LINE_OFFICER,

    @SerializedName("recruitment_officer")
    RECRUITMENT_OFFICER,

    @SerializedName("executive_officer")
    DEPUTY_COMMANDER,

    @SerializedName("commander")
    COMMANDER,
}