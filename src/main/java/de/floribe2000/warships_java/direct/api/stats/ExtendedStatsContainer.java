package de.floribe2000.warships_java.direct.api.stats;

/**
 * Extended stats container extends the general stats container with another set of non-operations
 * values, but are not part of ranked stats located in the seasons package.
 *
 * @author SirLefti
 */
public class ExtendedStatsContainer extends GeneralStatsContainer {

	private int max_damage_dealt_to_buildings = 0;

	private int damage_to_buildings = 0;

	private int max_damage_scouting = 0;

	private int damage_scouting = 0;

	private int max_ships_spotted = 0;

	private int ships_spotted = 0;

	private int max_suppressions_count = 0;

	private int suppressions_count = 0;

	private int capture_points = 0;

	private int team_capture_points = 0;

	private int dropped_capture_points = 0;

	private int team_dropped_capture_points = 0;

    private long art_agro = 0;

    private long torpedo_agro = 0;

    private long max_total_agro = 0;

    private int battles_since_510 = 0;

    private int battles_since_512 = 0;

    public int getMax_damage_dealt_to_buildings() {
        return this.max_damage_dealt_to_buildings;
    }

    public int getDamage_to_buildings() {
        return this.damage_to_buildings;
    }

    public int getMax_damage_scouting() {
        return this.max_damage_scouting;
    }

    public int getDamage_scouting() {
        return this.damage_scouting;
    }

    public int getMax_ships_spotted() {
        return this.max_ships_spotted;
    }

    public int getShips_spotted() {
        return this.ships_spotted;
    }

    public int getMax_suppressions_count() {
        return this.max_suppressions_count;
    }

    public int getSuppressions_count() {
        return this.suppressions_count;
    }

    public int getCapture_points() {
        return this.capture_points;
    }

    public int getTeam_capture_points() {
        return this.team_capture_points;
    }

    public int getDropped_capture_points() {
        return this.dropped_capture_points;
    }

    public int getTeam_dropped_capture_points() {
        return this.team_dropped_capture_points;
    }

    public long getArt_agro() {
        return this.art_agro;
    }

    public long getTorpedo_agro() {
        return this.torpedo_agro;
    }

    public long getMax_total_agro() {
        return this.max_total_agro;
    }

    public int getBattles_since_510() {
        return this.battles_since_510;
    }

    public int getBattles_since_512() {
        return this.battles_since_512;
    }
}
