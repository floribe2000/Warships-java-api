package de.floribe2000.warships_java.direct.seasons;

import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.Meta;

import de.floribe2000.warships_java.direct.api.stats.ExtendedWeaponStats;
import de.floribe2000.warships_java.direct.api.stats.WeaponStats;
import java.util.List;
import java.util.Map;

import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;
import lombok.Getter;

/**
 * A representation of the api result of ranked battles ships statistics.
 * This class holds all data returned by this request to allow easy access to this information
 *
 * @author SirLefti
 */
@Getter
public class RankedBattlesShipsStatistics implements IApiResponse {

	/**
	 * Response status
	 */
	private Status status;

	/**
	 * Response meta data
	 */
	private Meta meta;

	/**
	 * Map containing request objects
	 */
	private Map<Integer, List<ShipElement>> data;

	@Getter
	public static class ShipElement {

		/**
		 * players accountId
		 */
		private int account_id;

		/**
		 * ship id
		 */
		private long ship_id;

		/**
		 * Map containing request objects
		 */
		private Map<Integer, StatsContainer> seasons;

		@Getter
		public static class StatsContainer {

			/**
			 * Info about season results
			 */
			private static SeasonInfo rank_info;

			private static ModeStats rank_div3;

			private static ModeStats rank_solo;

			private static ModeStats rank_div2;

		}

		@Getter
		public static class ModeStats {

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
			private ExtendedWeaponStats torpedoes;

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
			private WeaponStats aircraft;

			/**
			 * ramming stats
			 */
			private WeaponStats ramming;

			/**
			 * main battery stats
			 */
			private ExtendedWeaponStats main_battery;

			/**
			 * secondary battery stats
			 */
			private ExtendedWeaponStats secondary_battery;

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

		@Getter
		public static class SeasonInfo {

			/**
			 * max reached rank
			 */
			private int max_rank;

			/**
			 * start rank
			 */
			private int start_rank;

			/**
			 * stars
			 */
			private int stars;

			/**
			 * current/final rank
			 */
			private int rank;

			/**
			 * stage
			 */
			private int stage;
		}
	}
}
