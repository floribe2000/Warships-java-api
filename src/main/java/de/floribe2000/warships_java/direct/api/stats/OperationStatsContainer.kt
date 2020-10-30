package de.floribe2000.warships_java.direct.api.stats;

import java.util.HashMap;
import java.util.Map;

/**
 * Operation stats container extends the base with wins_by_tasks map.
 *
 * @author SirLefti
 */
public class OperationStatsContainer extends BaseStatsContainer {

    private Map<Integer, Integer> wins_by_tasks = new HashMap<>();

    public Map<Integer, Integer> getWins_by_tasks() {
        return this.wins_by_tasks;
    }
}
