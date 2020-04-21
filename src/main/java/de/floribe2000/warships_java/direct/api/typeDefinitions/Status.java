package de.floribe2000.warships_java.direct.api.typeDefinitions;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;

/**
 * An enum to indicate the response status of an api request.
 *
 * @author floribe2000
 * @since 0.2.9
 */
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
