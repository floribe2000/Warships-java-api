package de.floribe2000.warships_java.direct.api.stats

/**
 * General stats container extends the base with non-operations values.
 *
 * @author SirLefti
 */
@Suppress("PropertyName", "PropertyName")
interface GeneralStatsContainer : BaseStatsContainer {
    val draws: Int
    val max_xp: Int
    val max_damage_dealt: Int
    val damage_dealt: Long
    val max_planes_killed: Int
    val planes_killed: Int
    val max_frags_battle: Int
    val frags: Int
}