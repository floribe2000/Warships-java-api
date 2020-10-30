package de.floribe2000.warships_java.direct.api.stats

import de.floribe2000.warships_java.direct.api.stats.implementation.ExtendedWeaponStatsImpl
import de.floribe2000.warships_java.direct.api.stats.implementation.WeaponStatsImpl

/**
 * Ship stats container extends the extended stats container with weapon stats for ships.
 *
 * @author SirLefti
 */
class ShipStatsContainer : ExtendedStatsContainer() {
    val main_battery: ExtendedWeaponStatsImpl? = null
    val second_battery: ExtendedWeaponStatsImpl? = null
    val torpedoes: ExtendedWeaponStatsImpl? = null
    val ramming: WeaponStatsImpl? = null
    val aircraft: WeaponStatsImpl? = null
}