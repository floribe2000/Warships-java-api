package de.floribe2000.warships_java.direct.api.stats;

import lombok.Getter;

/**
 * Base stats container for all requests that provide stats in some way. More detailed responses
 * will inherit this container.
 *
 * @author SirLefti
 */
@Getter
public class BaseStatsContainer {

	private int wins = 0;

	private int losses = 0;

	private int battles = 0;

	private int survived_wins = 0;

	private int survived_battles = 0;

	private int xp = 0;

}
