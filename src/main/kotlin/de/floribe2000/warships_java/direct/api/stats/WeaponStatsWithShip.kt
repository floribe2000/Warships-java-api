package de.floribe2000.warships_java.direct.api.stats

/**
 * Base weapon stats container for all weapon types.
 *
 * @author SirLefti
 */
@Suppress("PropertyName", "PropertyName")
interface WeaponStatsWithShip : WeaponStats {
    val max_frags_ship_id: Long?
}