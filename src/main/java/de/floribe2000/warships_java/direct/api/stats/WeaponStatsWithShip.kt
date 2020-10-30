package de.floribe2000.warships_java.direct.api.stats

/**
 * Base weapon stats container for all weapon types.
 *
 * @author SirLefti
 */
interface WeaponStatsWithShip {
    val frags: Int
    val max_frags_battle: Int
    val max_frags_ship_id: Long
}