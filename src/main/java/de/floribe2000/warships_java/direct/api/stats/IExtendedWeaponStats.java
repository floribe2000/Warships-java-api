package de.floribe2000.warships_java.direct.api.stats;

/**
 * An interface that solely exists for the purpose to put extended weapon stats containers in one
 * single collection, no matter if they come from a ship or player
 *
 * @author SirLefti
 */
public interface IExtendedWeaponStats extends IWeaponStats {

	int getShots();
	int getHits();

}
