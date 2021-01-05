package de.floribe2000.warships_java.direct.api.stats

import de.floribe2000.warships_java.direct.api.stats.implementation.ExtendedWeaponStatsImpl
import de.floribe2000.warships_java.direct.api.stats.implementation.WeaponStatsImpl
import kotlinx.serialization.Serializable

/**
 * Ship stats container extends the extended stats container with weapon stats for ships.
 *
 * @author SirLefti
 */
@Serializable
data class ShipStatsContainer(
    override val wins: Int = -1,
    override val losses: Int = -1,
    override val battles: Int = -1,
    override val survived_wins: Int = -1,
    override val survived_battles: Int = -1,
    override val xp: Long = -1,
    override val max_damage_dealt_to_buildings: Int = -1,
    override val damage_to_buildings: Long = -1,
    override val max_damage_scouting: Int = -1,
    override val damage_scouting: Long = -1,
    override val max_ships_spotted: Int = -1,
    override val ships_spotted: Int = -1,
    override val max_suppressions_count: Int = -1,
    override val suppressions_count: Int = -1,
    override val capture_points: Long = -1,
    override val team_capture_points: Long = -1,
    override val dropped_capture_points: Long = -1,
    override val team_dropped_capture_points: Long = -1,
    override val art_agro: Long = -1,
    override val torpedo_agro: Long = -1,
    override val max_total_agro: Long = -1,
    override val battles_since_510: Int = -1,
    override val battles_since_512: Int = -1,
    override val draws: Int = -1,
    override val max_xp: Int = -1,
    override val max_damage_dealt: Int = -1,
    override val damage_dealt: Long = -1,
    override val max_planes_killed: Int = -1,
    override val planes_killed: Int = -1,
    override val max_frags_battle: Int = -1,
    override val frags: Int = -1,

    val main_battery: ExtendedWeaponStatsImpl = ExtendedWeaponStatsImpl(),
    val second_battery: ExtendedWeaponStatsImpl = ExtendedWeaponStatsImpl(),
    val torpedoes: ExtendedWeaponStatsImpl = ExtendedWeaponStatsImpl(),
    val ramming: WeaponStatsImpl = WeaponStatsImpl(),
    val aircraft: WeaponStatsImpl = WeaponStatsImpl(),
) : ExtendedStatsContainer