package de.floribe2000.warships_java.direct.account;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.stats.ExtendedWeaponStatsWithShip;
import de.floribe2000.warships_java.direct.api.stats.OperationStatsContainer;
import de.floribe2000.warships_java.direct.api.stats.PlayerStatsContainer;
import de.floribe2000.warships_java.direct.api.stats.WeaponStatsWithShip;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;
import java.util.Map;
import lombok.Getter;

/**
 * A representation of a full data set from the api. Contains all available fields for player and
 * player pvp stats.
 */
@Getter
public class PlayersPersonalDataFull implements IApiResponse {

	/**
	 * The response status from the api
	 */
	private Status status = Status.ERROR;

	/**
	 * Details about errors in case of a failed request.
	 * <p>Field is null if no errors occurred during the request!</p>
	 */
	private ErrorContainer error = null;

	/**
	 * The meta object of the api response
	 */
	private Meta meta = null;

	private Map<String, PlayerDetails> data = null;

	/**
	 * A representation of individual player statistics.
	 */
	@Getter
	public static class PlayerDetails {

		private int last_battle_time = 0;

		private long account_id = 0;

		private int leveling_tier = 0;

		private int created_at = 0;

		private boolean hidden_profile = false;

		private int logout_at = 0;

		private Statistics statistics = null;

		/**
		 * A representation of the player's battle statistics
		 */
		@Getter
		public static class Statistics {

			private int distance = 0;

			private int battles = 0;

			private PlayerStatsContainer pvp = null;

			private PlayerStatsContainer pve = null;

			private PlayerStatsContainer pvp_div2 = null;

			private PlayerStatsContainer pvp_div3 = null;

			private PlayerStatsContainer pve_div2 = null;

			private PlayerStatsContainer pve_div3 = null;

			private PlayerStatsContainer rank_solo = null;

			private PlayerStatsContainer rank_div2 = null;

			private PlayerStatsContainer rank_div3 = null;

			private OperationStatsContainer oper_solo = null;

			private OperationStatsContainer oper_div = null;

			private OperationStatsContainer oper_div_hard = null;

		}

		private String nickname = null;

		private long stats_updated_at = 0;
	}

	@Override
	public String toString() {
		return IRequestAction.GSON.toJson(this);
	}
}
