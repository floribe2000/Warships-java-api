package de.floribe2000.warships_java.direct.api.stats.implementation

import de.floribe2000.warships_java.direct.api.stats.ExtendedWeaponStatsWithShip
import kotlinx.serialization.Serializable

@Serializable
data class ExtendedWeaponStatsWithShipImpl(
    override val frags: Int = 0,
    override val max_frags_battle: Int = 0,
    override val max_frags_ship_id: Long? = 0,
    override val hits: Int = 0,
    override val shots: Int = 0,

    ) : ExtendedWeaponStatsWithShip