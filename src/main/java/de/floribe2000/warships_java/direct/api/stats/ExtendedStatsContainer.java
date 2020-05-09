package de.floribe2000.warships_java.direct.api.stats;

import lombok.Getter;

/**
 * Extended stats container extends the general stats container with another set of non-operations
 * values, but are not part of ranked stats located in the seasons package.
 *
 * @author SirLefti
 */
@Getter
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

}
