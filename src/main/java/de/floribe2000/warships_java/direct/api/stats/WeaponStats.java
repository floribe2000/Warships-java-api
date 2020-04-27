package de.floribe2000.warships_java.direct.api.stats;

import lombok.Getter;

/**
 * Container containing base weapon stats
 *
 * @author SirLefti
 */
@Getter
public class WeaponStats {

	private int frags = 0;
	private int max_frags_battle = 0;
	private long max_frags_ship_id = 0;
}
