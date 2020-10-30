package de.floribe2000.warships_java.direct.api.stats

import de.floribe2000.warships_java.direct.api.stats.implementation.ExtendedWeaponStatsWithShipImpl
import de.floribe2000.warships_java.direct.api.stats.implementation.WeaponStatsWithShipImpl

/**
 * Player stats container extends the extended stats container with weapon stats for players and
 * some additional values, mainly the ship ids for best performing ships.
 *
 * @author SirLefti
 */
class PlayerStatsContainer : ExtendedStatsContainer() {
    val main_battery: ExtendedWeaponStatsWithShipImpl? = null
    val second_battery: ExtendedWeaponStatsWithShipImpl? = null
    val torpedoes: ExtendedWeaponStatsWithShipImpl? = null
    val ramming: WeaponStatsWithShipImpl? = null
    val aircraft: WeaponStatsWithShipImpl? = null
    val control_captured_points: Long = 0
    val control_dropped_points: Long = 0
    val max_ships_spotted_ship_id: Long = 0
    val max_xp_ship_id: Long = 0
    val max_frags_ship_id: Long = 0
    val max_total_agro_ship_id: Long = 0
    val max_suppressions_ship_id: Long = 0
    val max_planes_killed_ship_id: Long = 0
    val max_damage_dealt_to_buildings_ship_id: Long = 0
    val max_damage_dealt_ship_id: Long = 0
    val max_scouting_damage_ship_id: Long = 0
}