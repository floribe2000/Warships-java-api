package de.floribe2000.warships_java.direct.encyclopedia

import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status
import kotlinx.serialization.Serializable

@Serializable
data class Achievements(
    val status: Status = Status.ERROR,

    /**
     * Details about errors in case of a failed request.
     *
     * Field is null if no errors occurred during the request!
     */
    val error: ErrorContainer? = null,
    val meta: Meta? = null,
    val data: DataEntry? = null,
) {
    @Serializable
    data class DataEntry(
        val battle: Map<String, AchievementSingle>? = null,
    )

    @Serializable
    data class AchievementSingle(
        val name: String? = null,
    )
}