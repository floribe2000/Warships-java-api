package de.floribe2000.warships_java.direct.api.stats

import de.floribe2000.warships_java.direct.api.stats.implementation.ExtendedWeaponStatsImpl
import de.floribe2000.warships_java.direct.api.stats.implementation.WeaponStatsImpl

/**
 * Ranked stats container extends the general stats container with weapon stats. This container will
 * be used by the requests located in the seasons package.
 *
 * @author SirLefti
 */
class RankedStatsContainer : GeneralStatsContainer() {
    val main_battery: ExtendedWeaponStatsImpl? = null
    val second_battery: ExtendedWeaponStatsImpl? = null
    val torpedoes: ExtendedWeaponStatsImpl? = null
    val ramming: WeaponStatsImpl? = null
    val aircraft: WeaponStatsImpl? = null
}