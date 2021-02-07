package de.floribe2000.warships_java.direct.encyclopedia

import de.floribe2000.warships_java.direct.api.defaultJsonFormatter
import de.floribe2000.warships_java.direct.api.typeDefinitions.Nation
import de.floribe2000.warships_java.direct.api.typeDefinitions.ShipType
import de.floribe2000.warships_java.direct.api.typeDefinitions.Tier
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString

/**
 * A reduced view of a [ShipEntry][de.floribe2000.warships_java.direct.encyclopedia.Warships.ShipEntry].
 */
@Suppress("UNUSED")
@Serializable
data class ShipEntryReduced(
    val shipId: Long,
    val name: String?,
    val tier: Tier?,
    val nation: Nation?,
    val type: ShipType?,
    val isDemoProfile: Boolean,
    val isPremium: Boolean,
    val isSpecial: Boolean,
    val shipIdStr: String?,
) {

    constructor(fullDetails: Warships.ShipEntry) : this(
        fullDetails.shipId,
        fullDetails.name,
        fullDetails.tier,
        fullDetails.nation,
        fullDetails.type,
        fullDetails.hasDemoProfile,
        fullDetails.isPremium,
        fullDetails.isSpecial,
        fullDetails.shipIdStr,
    )

    override fun toString(): String {
        return defaultJsonFormatter.encodeToString(this)
    }

}