package de.floribe2000.warships_java.direct.api.stats;

import de.floribe2000.warships_java.direct.api.stats.implementation.ExtendedWeaponStatsImpl;
import de.floribe2000.warships_java.direct.api.stats.implementation.WeaponStatsImpl;
import lombok.Getter;

/**
 * Ranked stats container extends the general stats container with weapon stats. This container will
 * be used by the requests located in the seasons package.
 *
 * @author SirLefti
 */
@Getter
public class RankedStatsContainer extends GeneralStatsContainer {

	private ExtendedWeaponStatsImpl main_battery = null;
	private ExtendedWeaponStatsImpl second_battery = null;
	private ExtendedWeaponStatsImpl torpedoes = null;
	private WeaponStatsImpl ramming = null;
	private WeaponStatsImpl aircraft = null;

}
