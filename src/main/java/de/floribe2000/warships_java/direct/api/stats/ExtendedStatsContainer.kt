package de.floribe2000.warships_java.direct.api.stats

/**
 * Extended stats container extends the general stats container with another set of non-operations
 * values, but are not part of ranked stats located in the seasons package.
 *
 * @author SirLefti
 */
abstract class ExtendedStatsContainer : GeneralStatsContainer() {
    val max_damage_dealt_to_buildings = 0
    val damage_to_buildings = 0
    val max_damage_scouting = 0
    val damage_scouting = 0
    val max_ships_spotted = 0
    val ships_spotted = 0
    val max_suppressions_count = 0
    val suppressions_count = 0
    val capture_points = 0
    val team_capture_points = 0
    val dropped_capture_points = 0
    val team_dropped_capture_points = 0
    val art_agro: Long = 0
    val torpedo_agro: Long = 0
    val max_total_agro: Long = 0
    val battles_since_510 = 0
    val battles_since_512 = 0
}