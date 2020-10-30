package de.floribe2000.warships_java.direct.api.stats;

import de.floribe2000.warships_java.direct.api.stats.implementation.ExtendedWeaponStatsImpl;
import de.floribe2000.warships_java.direct.api.stats.implementation.WeaponStatsImpl;

/**
 * Ship stats container extends the extended stats container with weapon stats for ships.
 *
 * @author SirLefti
 */
public class ShipStatsContainer extends ExtendedStatsContainer {

    private ExtendedWeaponStatsImpl main_battery = null;

    private ExtendedWeaponStatsImpl second_battery = null;

    private ExtendedWeaponStatsImpl torpedoes = null;

    private WeaponStatsImpl ramming = null;

    private WeaponStatsImpl aircraft = null;

    public ExtendedWeaponStatsImpl getMain_battery() {
        return this.main_battery;
    }

    public ExtendedWeaponStatsImpl getSecond_battery() {
        return this.second_battery;
    }

    public ExtendedWeaponStatsImpl getTorpedoes() {
        return this.torpedoes;
    }

    public WeaponStatsImpl getRamming() {
        return this.ramming;
    }

    public WeaponStatsImpl getAircraft() {
        return this.aircraft;
    }
}
