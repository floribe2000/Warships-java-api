package de.floribe2000.warships_java.direct.api

import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON
import kotlinx.serialization.Serializable

/**
 * A representation of the error field of a failed api request.
 *
 * @author floribe2000
 */
@Serializable
class ErrorContainer {
    /**
     * The request field that caused the error.
     */
    val field: String? = null

    /**
     * The error message returned by the api.
     */
    val message: String? = null

    /**
     * The error code returned by the api.
     */
    val code = 0

    /**
     * The invalid value, might be null.
     */
    val value: String? = null

    override fun toString(): String {
        return GSON.toJson(this)
    }
}