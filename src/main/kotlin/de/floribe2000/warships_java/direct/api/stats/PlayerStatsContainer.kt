package de.floribe2000.warships_java.direct.api.stats

import de.floribe2000.warships_java.direct.api.stats.implementation.ExtendedWeaponStatsWithShipImpl
import de.floribe2000.warships_java.direct.api.stats.implementation.WeaponStatsWithShipImpl
import kotlinx.serialization.Serializable

/**
 * Player stats container extends the extended stats container with weapon stats for players and
 * some additional values, mainly the ship ids for best performing ships.
 *
 * @author SirLefti
 */
@Serializable
data class PlayerStatsContainer(
    val main_battery: ExtendedWeaponStatsWithShipImpl? = null,
    val second_battery: ExtendedWeaponStatsWithShipImpl? = null,
    val torpedoes: ExtendedWeaponStatsWithShipImpl? = null,
    val ramming: WeaponStatsWithShipImpl? = null,
    val aircraft: WeaponStatsWithShipImpl? = null,
    val control_captured_points: Long = 0,
    val control_dropped_points: Long = 0,
    val max_ships_spotted_ship_id: Long? = 0,
    val max_xp_ship_id: Long? = 0,
    val max_frags_ship_id: Long? = 0,
    val max_total_agro_ship_id: Long? = 0,
    val max_suppressions_ship_id: Long? = 0,
    val max_planes_killed_ship_id: Long? = 0,
    val max_damage_dealt_to_buildings_ship_id: Long? = 0,
    val max_damage_dealt_ship_id: Long? = 0,
    val max_scouting_damage_ship_id: Long? = 0,

    override val wins: Int = 0,
    override val losses: Int = 0,
    override val battles: Int = 0,
    override val survived_wins: Int = 0,
    override val survived_battles: Int = 0,
    override val xp: Long = 0,
    override val max_damage_dealt_to_buildings: Int = 0,
    override val damage_to_buildings: Long = 0,
    override val max_damage_scouting: Int = 0,
    override val damage_scouting: Long = 0,
    override val max_ships_spotted: Int = 0,
    override val ships_spotted: Int = 0,
    override val max_suppressions_count: Int = 0,
    override val suppressions_count: Int = 0,
    override val capture_points: Long = 0,
    override val team_capture_points: Long = 0,
    override val dropped_capture_points: Long = 0,
    override val team_dropped_capture_points: Long = 0,
    override val art_agro: Long = 0,
    override val torpedo_agro: Long = 0,
    override val max_total_agro: Long = 0,
    override val battles_since_510: Int = 0,
    override val battles_since_512: Int = 0,
    override val draws: Int = 0,
    override val max_xp: Int = 0,
    override val max_damage_dealt: Int = 0,
    override val damage_dealt: Long = 0,
    override val max_planes_killed: Int = 0,
    override val planes_killed: Int = 0,
    override val max_frags_battle: Int = 0,
    override val frags: Int = 0,
) : ExtendedStatsContainer