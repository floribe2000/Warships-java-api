package de.floribe2000.warships_java.direct.api.stats.implementation;

import de.floribe2000.warships_java.direct.api.stats.ExtendedWeaponStats;
import de.floribe2000.warships_java.direct.api.stats.ExtendedWeaponStatsWithShip;
import de.floribe2000.warships_java.direct.api.stats.WeaponStats;
import de.floribe2000.warships_java.direct.api.stats.WeaponStatsWithShip;

public class ExtendedWeaponStatsWithShipImpl implements ExtendedWeaponStatsWithShip, WeaponStatsWithShip,
        ExtendedWeaponStats, WeaponStats {

    private int frags = 0;

    private int max_frags_battle = 0;

    private long max_frags_ship_id = 0;

    private int hits = 0;

    private int shots = 0;

    public int getFrags() {
        return this.frags;
    }

    public int getMax_frags_battle() {
        return this.max_frags_battle;
    }

    public long getMax_frags_ship_id() {
        return this.max_frags_ship_id;
    }

    public int getHits() {
        return this.hits;
    }

    public int getShots() {
        return this.shots;
    }
}
