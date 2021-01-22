package de.floribe2000.warships_java.direct.api.stats

/**
 * Extended stats container extends the general stats container with another set of non-operations
 * values, but are not part of ranked stats located in the seasons package.
 *
 * @author SirLefti
 */
@Suppress("PropertyName", "PropertyName")
interface ExtendedStatsContainer : GeneralStatsContainer {
    val max_damage_dealt_to_buildings: Int
    val damage_to_buildings: Long
    val max_damage_scouting: Int
    val damage_scouting: Long
    val max_ships_spotted: Int
    val ships_spotted: Int
    val max_suppressions_count: Int
    val suppressions_count: Int
    val capture_points: Long
    val team_capture_points: Long
    val dropped_capture_points: Long
    val team_dropped_capture_points: Long
    val art_agro: Long
    val torpedo_agro: Long
    val max_total_agro: Long
    val battles_since_510: Int
    val battles_since_512: Int
}