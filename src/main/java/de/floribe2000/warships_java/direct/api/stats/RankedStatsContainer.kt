package de.floribe2000.warships_java.direct.api.stats;

import de.floribe2000.warships_java.direct.api.stats.implementation.ExtendedWeaponStatsImpl;
import de.floribe2000.warships_java.direct.api.stats.implementation.WeaponStatsImpl;

/**
 * Ranked stats container extends the general stats container with weapon stats. This container will
 * be used by the requests located in the seasons package.
 *
 * @author SirLefti
 */
public class RankedStatsContainer extends GeneralStatsContainer {

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
