package de.floribe2000.warships_java.direct.api.stats.implementation;

import de.floribe2000.warships_java.direct.api.stats.WeaponStats;
import lombok.Getter;

@Getter
public class WeaponStatsImpl implements WeaponStats {

	private int frags = 0;
	private int max_frags_battle = 0;

}
