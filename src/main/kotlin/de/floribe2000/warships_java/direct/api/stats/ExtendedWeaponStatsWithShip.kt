package de.floribe2000.warships_java.direct.api.stats

/**
 * Extended weapon stats container for all weapon types that count hits and shots.
 *
 * @author SirLefti
 */
interface ExtendedWeaponStatsWithShip : WeaponStatsWithShip {
    val hits: Int
    val shots: Int
}