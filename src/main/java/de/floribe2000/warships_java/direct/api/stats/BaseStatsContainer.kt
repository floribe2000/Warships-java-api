package de.floribe2000.warships_java.direct.api.stats

/**
 * Base stats container for all requests that provide stats in some way. More detailed responses
 * will inherit this container.
 *
 * @author SirLefti
 */
abstract class BaseStatsContainer {
    open val wins: Int = 0
    open val losses: Int = 0
    open val battles: Int = 0
    open val survived_wins: Int = 0
    open val survived_battles: Int = 0
    open val xp: Long = 0
}