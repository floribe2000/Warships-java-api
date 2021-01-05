package de.floribe2000.warships_java.direct.api.stats.implementation

import de.floribe2000.warships_java.direct.api.stats.WeaponStats
import de.floribe2000.warships_java.direct.api.stats.WeaponStatsWithShip
import kotlinx.serialization.Serializable

@Serializable
data class WeaponStatsWithShipImpl(
    override val frags: Int = 0,
    override val max_frags_battle: Int = 0,
    override val max_frags_ship_id: Long? = 0,
) : WeaponStatsWithShip, WeaponStats