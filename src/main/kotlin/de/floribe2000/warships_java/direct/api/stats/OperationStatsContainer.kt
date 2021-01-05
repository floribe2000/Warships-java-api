package de.floribe2000.warships_java.direct.api.stats

import kotlinx.serialization.Serializable
import java.util.*

/**
 * Operation stats container extends the base with wins_by_tasks map.
 *
 * @author SirLefti
 */
@Serializable
class OperationStatsContainer(
    override val wins: Int,
    override val losses: Int,
    override val battles: Int,
    override val survived_wins: Int,
    override val survived_battles: Int,
    override val xp: Long,

    val wins_by_tasks: Map<Int, Int> = mapOf(),
) : BaseStatsContainer