package de.floribe2000.warships_java.direct.api.stats.implementation;

import de.floribe2000.warships_java.direct.api.stats.ExtendedWeaponStats;
import de.floribe2000.warships_java.direct.api.stats.WeaponStats;

public class ExtendedWeaponStatsImpl implements ExtendedWeaponStats, WeaponStats {

    private int frags = 0;

    private int max_frags_battle = 0;

    private int hits = 0;

    private int shots = 0;

    public int getFrags() {
        return this.frags;
    }

    public int getMax_frags_battle() {
        return this.max_frags_battle;
    }

    public int getHits() {
        return this.hits;
    }

    public int getShots() {
        return this.shots;
    }
}
