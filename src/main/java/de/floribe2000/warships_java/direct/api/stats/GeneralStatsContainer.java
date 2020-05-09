package de.floribe2000.warships_java.direct.api.stats;

import lombok.Getter;

/**
 * General stats container extends the base with non-operations values.
 *
 * @author SirLefti
 */
@Getter
public class GeneralStatsContainer extends BaseStatsContainer {

	private int draws = 0;

	private int max_xp = 0;

	private int max_damage_dealt = 0;

	private long damage_dealt = 0;

	private int max_planes_killed = 0;

	private int planes_killed = 0;

	private int max_frags_battle = 0;

	private int frags = 0;

}
