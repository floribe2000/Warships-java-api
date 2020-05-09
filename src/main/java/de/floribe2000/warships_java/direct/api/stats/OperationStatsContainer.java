package de.floribe2000.warships_java.direct.api.stats;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

/**
 * Operation stats container extends the base with wins_by_tasks map.
 *
 * @author SirLefti
 */
@Getter
public class OperationStatsContainer extends BaseStatsContainer {

	private Map<Integer, Integer> wins_by_tasks = new HashMap<>();
}
