package de.floribe2000.warships_java.direct.api.stats;

/**
 * Base weapon stats container for all weapon types.
 *
 * @author SirLefti
 */
public interface WeaponStatsWithShip {

	int getFrags();
	int getMax_frags_battle();
	long getMax_frags_ship_id();
}
