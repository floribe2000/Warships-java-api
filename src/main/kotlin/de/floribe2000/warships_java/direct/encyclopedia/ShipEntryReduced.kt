package de.floribe2000.warships_java.direct.encyclopedia

import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON
import de.floribe2000.warships_java.direct.api.typeDefinitions.Nation
import de.floribe2000.warships_java.direct.api.typeDefinitions.ShipType
import de.floribe2000.warships_java.direct.api.typeDefinitions.Tier

/**
 * A reduced view of a [ShipEntry][de.floribe2000.warships_java.direct.encyclopedia.Warships.ShipEntry].
 */
class ShipEntryReduced(fullDetails: Warships.ShipEntry) {

    val shipId: Long = fullDetails.shipId
    val name: String? = fullDetails.name
    val tier: Tier? = fullDetails.tier
    val nation: Nation? = fullDetails.nation
    val type: ShipType? = fullDetails.type
    val isDemoProfile: Boolean = fullDetails.hasDemoProfile
    val isPremium: Boolean = fullDetails.isPremium
    val isSpecial: Boolean = fullDetails.isSpecial
    val shipIdStr: String? = fullDetails.shipIdStr

    override fun toString(): String {
        return GSON.toJson(this)
    }

}