package de.floribe2000.warships_java.direct.api.stats.implementation

import de.floribe2000.warships_java.direct.api.stats.ExtendedWeaponStats
import de.floribe2000.warships_java.direct.api.stats.ExtendedWeaponStatsWithShip
import de.floribe2000.warships_java.direct.api.stats.WeaponStats
import de.floribe2000.warships_java.direct.api.stats.WeaponStatsWithShip

class ExtendedWeaponStatsWithShipImpl : ExtendedWeaponStatsWithShip, WeaponStatsWithShip, ExtendedWeaponStats, WeaponStats {
    override val frags = 0
    override val max_frags_battle = 0
    override val max_frags_ship_id: Long = 0
    override val hits = 0
    override val shots = 0
}