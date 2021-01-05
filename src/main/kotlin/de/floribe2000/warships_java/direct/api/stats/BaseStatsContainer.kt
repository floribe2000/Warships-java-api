package de.floribe2000.warships_java.direct.api.stats

/**
 * Base stats container for all requests that provide stats in some way. More detailed responses
 * will inherit this container.
 *
 * @author SirLefti
 */
interface BaseStatsContainer {
    val wins: Int
    val losses: Int
    val battles: Int
    val survived_wins: Int
    val survived_battles: Int
    val xp: Long
}