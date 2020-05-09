package de.floribe2000.warships_java.direct.api.stats.implementation;

import de.floribe2000.warships_java.direct.api.stats.ExtendedWeaponStats;
import de.floribe2000.warships_java.direct.api.stats.WeaponStats;
import lombok.Getter;

@Getter
public class ExtendedWeaponStatsImpl implements ExtendedWeaponStats, WeaponStats {

	private int frags = 0;
	private int max_frags_battle = 0;

	private int hits = 0;
	private int shots = 0;

}
