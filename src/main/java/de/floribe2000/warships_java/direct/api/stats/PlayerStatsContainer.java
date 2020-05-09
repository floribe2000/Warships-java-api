package de.floribe2000.warships_java.direct.api.stats;

import de.floribe2000.warships_java.direct.api.stats.implementation.ExtendedWeaponStatsWithShipImpl;
import de.floribe2000.warships_java.direct.api.stats.implementation.WeaponStatsWithShipImpl;
import lombok.Getter;

/**
 * Player stats container extends the extended stats container with weapon stats for players and
 * some additional values, mainly the ship ids for best performing ships.
 *
 * @author SirLefti
 */
@Getter
public class PlayerStatsContainer extends ExtendedStatsContainer {

	private ExtendedWeaponStatsWithShipImpl main_battery = null;
	private ExtendedWeaponStatsWithShipImpl second_battery = null;
	private ExtendedWeaponStatsWithShipImpl torpedoes = null;
	private WeaponStatsWithShipImpl ramming = null;
	private WeaponStatsWithShipImpl aircraft = null;

	private long control_captured_points = 0;

	private long control_dropped_points = 0;

	private long max_ships_spotted_ship_id = 0;

	private long max_xp_ship_id = 0;

	private long max_frags_ship_id = 0;

	private long max_total_agro_ship_id = 0;

	private long max_suppressions_ship_id = 0;

	private long max_planes_killed_ship_id = 0;

	private long max_damage_dealt_to_buildings_ship_id = 0;

	private long max_damage_dealt_ship_id = 0;

	private long max_scouting_damage_ship_id = 0;
}
