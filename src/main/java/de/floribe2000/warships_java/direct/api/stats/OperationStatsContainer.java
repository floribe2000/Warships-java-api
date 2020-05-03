package de.floribe2000.warships_java.direct.api.stats;

import java.util.Map;
import lombok.Getter;

@Getter
public class OperationStatsContainer extends BaseStatsContainer {

	private Map<String, Integer> wins_by_tasks = null;
}
