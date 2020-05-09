package de.floribe2000.warships_java.direct.api.stats;

import de.floribe2000.warships_java.direct.api.stats.implementation.ExtendedWeaponStatsImpl;
import de.floribe2000.warships_java.direct.api.stats.implementation.WeaponStatsImpl;
import lombok.Getter;

/**
 * Ship stats container extends the extended stats container with weapon stats for ships.
 *
 * @author SirLefti
 */
@Getter
public class ShipStatsContainer extends ExtendedStatsContainer {

	private ExtendedWeaponStatsImpl main_battery = null;
	private ExtendedWeaponStatsImpl second_battery = null;
	private ExtendedWeaponStatsImpl torpedoes = null;
	private WeaponStatsImpl ramming = null;
	private WeaponStatsImpl aircraft = null;

}
