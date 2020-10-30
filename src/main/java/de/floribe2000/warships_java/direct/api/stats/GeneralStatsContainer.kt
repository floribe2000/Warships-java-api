package de.floribe2000.warships_java.direct.api.stats

/**
 * General stats container extends the base with non-operations values.
 *
 * @author SirLefti
 */
abstract class GeneralStatsContainer : BaseStatsContainer() {
    open val draws = 0
    open val max_xp = 0
    open val max_damage_dealt = 0
    open val damage_dealt: Long = 0
    open val max_planes_killed = 0
    open val planes_killed = 0
    open val max_frags_battle = 0
    open val frags = 0
}