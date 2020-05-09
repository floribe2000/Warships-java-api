package de.floribe2000.warships_java.direct.api.stats.implementation;

import de.floribe2000.warships_java.direct.api.stats.WeaponStats;
import de.floribe2000.warships_java.direct.api.stats.WeaponStatsWithShip;
import lombok.Getter;

@Getter
public class WeaponStatsWithShipImpl implements WeaponStatsWithShip, WeaponStats {

	private int frags = 0;
	private int max_frags_battle = 0;

	private long max_frags_ship_id = 0;

}
