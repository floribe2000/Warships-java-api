package de.floribe2000.warships_java.direct.api.stats.implementation;

import de.floribe2000.warships_java.direct.api.stats.WeaponStats;
import de.floribe2000.warships_java.direct.api.stats.WeaponStatsWithShip;

public class WeaponStatsWithShipImpl implements WeaponStatsWithShip, WeaponStats {

    private int frags = 0;

    private int max_frags_battle = 0;

    private long max_frags_ship_id = 0;

    public int getFrags() {
        return this.frags;
    }

    public int getMax_frags_battle() {
        return this.max_frags_battle;
    }

    public long getMax_frags_ship_id() {
        return this.max_frags_ship_id;
    }
}
