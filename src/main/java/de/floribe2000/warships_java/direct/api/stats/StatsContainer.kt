package de.floribe2000.warships_java.direct.api.stats;

public class StatsContainer extends BaseStatsContainer {

	/**
	 * max frags in a battle
	 */
	private int max_frags_battle;

	/**
	 * total draws
	 */
	private int draws;

	/**
	 * max xp in a battle
	 */
	private int max_xp;

	/**
	 * total wins
	 */
	private int wins;

	/**
	 * total planes killed
	 */
	private int planes_killed;

	/**
	 * total losses
	 */
	private int losses;

	/**
	 * torpedo stats
	 */
	private ExtendedWeaponStatsWithShip torpedoes;

	/**
	 * total battles
	 */
	private int battles;

	/**
	 * max damage in a battle
	 */
	private int max_damage_dealt;

	/**
	 * total damage
	 */
	private int damage_dealt;

	/**
	 * max plane kills in a battle
	 */
	private int max_planes_killed;

	/**
	 * aircraft stats
	 */
	private WeaponStatsWithShip aircraft;

	/**
	 * ramming stats
	 */
	private WeaponStatsWithShip ramming;

	/**
	 * main battery stats
	 */
	private ExtendedWeaponStatsWithShip main_battery;

	/**
	 * secondary battery stats
	 */
	private ExtendedWeaponStatsWithShip secondary_battery;

	/**
	 * total survived wins
	 */
	private int survived_wins;

	/**
	 * total frags
	 */
	private int frags;

	/**
	 * gained xp
	 */
	private int xp;

	/**
	 * total survived battles
	 */
	private int survived_battles;

}
