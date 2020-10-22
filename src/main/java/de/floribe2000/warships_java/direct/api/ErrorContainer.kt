package de.floribe2000.warships_java.direct.api

import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON

/**
 * A representation of the error field of a failed api request.
 *
 * @author floribe2000
 */
class ErrorContainer {
    val field: String? = null
    val message: String? = null
    val code = 0
    val value: String? = null

    override fun toString(): String {
        return GSON.toJson(this)
    }
}