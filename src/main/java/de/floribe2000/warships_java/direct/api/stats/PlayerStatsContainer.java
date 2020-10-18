package de.floribe2000.warships_java.direct.api.stats;

import de.floribe2000.warships_java.direct.api.stats.implementation.ExtendedWeaponStatsWithShipImpl;
import de.floribe2000.warships_java.direct.api.stats.implementation.WeaponStatsWithShipImpl;

/**
 * Player stats container extends the extended stats container with weapon stats for players and
 * some additional values, mainly the ship ids for best performing ships.
 *
 * @author SirLefti
 */
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

    public ExtendedWeaponStatsWithShipImpl getMain_battery() {
        return this.main_battery;
    }

    public ExtendedWeaponStatsWithShipImpl getSecond_battery() {
        return this.second_battery;
    }

    public ExtendedWeaponStatsWithShipImpl getTorpedoes() {
        return this.torpedoes;
    }

    public WeaponStatsWithShipImpl getRamming() {
        return this.ramming;
    }

    public WeaponStatsWithShipImpl getAircraft() {
        return this.aircraft;
    }

    public long getControl_captured_points() {
        return this.control_captured_points;
    }

    public long getControl_dropped_points() {
        return this.control_dropped_points;
    }

    public long getMax_ships_spotted_ship_id() {
        return this.max_ships_spotted_ship_id;
    }

    public long getMax_xp_ship_id() {
        return this.max_xp_ship_id;
    }

    public long getMax_frags_ship_id() {
        return this.max_frags_ship_id;
    }

    public long getMax_total_agro_ship_id() {
        return this.max_total_agro_ship_id;
    }

    public long getMax_suppressions_ship_id() {
        return this.max_suppressions_ship_id;
    }

    public long getMax_planes_killed_ship_id() {
        return this.max_planes_killed_ship_id;
    }

    public long getMax_damage_dealt_to_buildings_ship_id() {
        return this.max_damage_dealt_to_buildings_ship_id;
    }

    public long getMax_damage_dealt_ship_id() {
        return this.max_damage_dealt_ship_id;
    }

    public long getMax_scouting_damage_ship_id() {
        return this.max_scouting_damage_ship_id;
    }
}
