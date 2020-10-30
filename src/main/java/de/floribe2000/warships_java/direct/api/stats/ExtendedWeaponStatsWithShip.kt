package de.floribe2000.warships_java.direct.api.stats;

/**
 * Extended weapon stats container for all weapon types that count hits and shots.
 *
 * @author SirLefti
 */
public interface ExtendedWeaponStatsWithShip {

	int getFrags();
	int getMax_frags_battle();
	long getMax_frags_ship_id();

	int getHits();
	int getShots();
}
