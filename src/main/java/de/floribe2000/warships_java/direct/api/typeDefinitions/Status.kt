package de.floribe2000.warships_java.direct.api.typeDefinitions;

import com.google.gson.annotations.SerializedName;

/**
 * An enum to indicate the response status of an api request.
 *
 * @author floribe2000
 * @since 0.2.9
 */
public enum Status {
    /**
     * Indicates a successful api request. The corresponding boolean value is true.
     */
    @SerializedName("ok")
    OK(true),

    /**
     * Indicates a failed api request. The corresponding boolean value is false.
     */
    @SerializedName("error")
    ERROR(false);

    /**
     * The boolean value associated with the status
     */
    private final boolean status;

    private Status(boolean status) {
        this.status = status;
    }

    /**
     * A method to get the boolean value of a status.
     *
     * @return the boolean value of the status
     */
    public boolean get() {
        return status;
    }

    /**
     * Parses a boolean and returns the corresponding status.
     *
     * @param value the boolean value of the status
     * @return the status for the boolean value
     */
    public Status parse(boolean value) {
        if (value) {
            return OK;
        } else {
            return ERROR;
        }
    }
}
