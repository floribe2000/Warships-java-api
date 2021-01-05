package de.floribe2000.warships_java.direct.api.stats

import de.floribe2000.warships_java.direct.api.stats.implementation.ExtendedWeaponStatsImpl
import de.floribe2000.warships_java.direct.api.stats.implementation.WeaponStatsImpl

/**
 * Ranked stats container extends the general stats container with weapon stats. This container will
 * be used by the requests located in the seasons package.
 *
 * @author SirLefti
 */
data class RankedStatsContainer(
    override val wins: Int,
    override val losses: Int,
    override val battles: Int,
    override val survived_wins: Int,
    override val survived_battles: Int,
    override val xp: Long,
    override val draws: Int,
    override val max_xp: Int,
    override val max_damage_dealt: Int,
    override val damage_dealt: Long,
    override val max_planes_killed: Int,
    override val planes_killed: Int,
    override val max_frags_battle: Int,
    override val frags: Int,

    val main_battery: ExtendedWeaponStatsImpl? = null,
    val second_battery: ExtendedWeaponStatsImpl? = null,
    val torpedoes: ExtendedWeaponStatsImpl? = null,
    val ramming: WeaponStatsImpl? = null,
    val aircraft: WeaponStatsImpl? = null,
) : GeneralStatsContainer