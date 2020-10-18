package de.floribe2000.warships_java.direct.api.stats.implementation;

import de.floribe2000.warships_java.direct.api.stats.WeaponStats;

public class WeaponStatsImpl implements WeaponStats {

    private int frags = 0;

    private int max_frags_battle = 0;

    public int getFrags() {
        return this.frags;
    }

    public int getMax_frags_battle() {
        return this.max_frags_battle;
    }
}
