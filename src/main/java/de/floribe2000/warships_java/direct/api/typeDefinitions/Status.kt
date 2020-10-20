package de.floribe2000.warships_java.direct.api.typeDefinitions

import com.google.gson.annotations.SerializedName

/**
 * An enum to indicate the response status of an api request.
 *
 * @author floribe2000
 * @since 0.2.9
 */
enum class Status(
        /**
         * The boolean value associated with the status
         */
        private val status: Boolean) {

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
     * A method to get the boolean value of a status.
     *
     * @return the boolean value of the status
     */
    fun get(): Boolean {
        return status
    }

    /**
     * Parses a boolean and returns the corresponding status.
     *
     * @param value the boolean value of the status
     * @return the status for the boolean value
     */
    fun parse(value: Boolean): Status {
        return if (value) {
            OK
        } else {
            ERROR
        }
    }
}