package de.floribe2000.warships_java.direct.api;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    @SerializedName("ok")
    OK(true),
    @SerializedName("error")
    ERROR(false);

    private final boolean status;

    public boolean get() {
        return status;
    }
}
