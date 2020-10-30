package de.floribe2000.warships_java.direct.api.stats

import java.util.*

/**
 * Operation stats container extends the base with wins_by_tasks map.
 *
 * @author SirLefti
 */
class OperationStatsContainer : BaseStatsContainer() {
    val wins_by_tasks: Map<Int, Int> = HashMap()
}