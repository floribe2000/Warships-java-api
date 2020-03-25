package de.floribe2000.warships_java.clans;

import com.google.gson.annotations.SerializedName;

public enum ClanRole {

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
    COMMANDER
}
