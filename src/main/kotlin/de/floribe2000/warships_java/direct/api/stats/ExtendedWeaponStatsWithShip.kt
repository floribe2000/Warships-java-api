package de.floribe2000.warships_java.direct.api.stats

/**
 * Extended weapon stats container for all weapon types that count hits and shots.
 *
 * @author SirLefti
 */
interface ExtendedWeaponStatsWithShip {
    val frags: Int
    val max_frags_battle: Int
    val max_frags_ship_id: Long
    val hits: Int
    val shots: Int
}