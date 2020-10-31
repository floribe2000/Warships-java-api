package de.floribe2000.warships_java.direct.encyclopedia

import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status

/**
 * This class is a representation of the result of an encyclopedia maps request.
 *
 * @author floribe2000
 * @since 0.2.13
 */
class Maps : IApiResponse {
    val status = Status.ERROR

    /**
     * Details about errors in case of a failed request.
     *
     * Field is null if no errors occurred during the request!
     */
    override val error: ErrorContainer? = null
    val meta: Meta? = null
    val data: Map<String, MapEntry>? = null

    data class MapEntry(
            val description: String,
            val icon: String,
            val battle_arena_id: Long,
            val name: String?,
    )

    override fun toString(): String {
        return GSON.toJson(this)
    }
}