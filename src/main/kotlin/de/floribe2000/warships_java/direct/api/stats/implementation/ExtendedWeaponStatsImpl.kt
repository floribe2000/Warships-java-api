package de.floribe2000.warships_java.direct.api.stats.implementation

import de.floribe2000.warships_java.direct.api.stats.ExtendedWeaponStats
import de.floribe2000.warships_java.direct.api.stats.WeaponStats
import kotlinx.serialization.Serializable

@Serializable
data class ExtendedWeaponStatsImpl(
    override val frags: Int = 0,
    override val max_frags_battle: Int = 0,
    override val hits: Int = 0,
    override val shots: Int = 0,
) : ExtendedWeaponStats, WeaponStats