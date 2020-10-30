package de.floribe2000.warships_java.direct.api.stats;

/**
 * General stats container extends the base with non-operations values.
 *
 * @author SirLefti
 */
public class GeneralStatsContainer extends BaseStatsContainer {

	private int draws = 0;

	private int max_xp = 0;

	private int max_damage_dealt = 0;

    private long damage_dealt = 0;

    private int max_planes_killed = 0;

    private int planes_killed = 0;

    private int max_frags_battle = 0;

    private int frags = 0;

    public int getDraws() {
        return this.draws;
    }

    public int getMax_xp() {
        return this.max_xp;
    }

    public int getMax_damage_dealt() {
        return this.max_damage_dealt;
    }

    public long getDamage_dealt() {
        return this.damage_dealt;
    }

    public int getMax_planes_killed() {
        return this.max_planes_killed;
    }

    public int getPlanes_killed() {
        return this.planes_killed;
    }

    public int getMax_frags_battle() {
        return this.max_frags_battle;
    }

    public int getFrags() {
        return this.frags;
    }
}
