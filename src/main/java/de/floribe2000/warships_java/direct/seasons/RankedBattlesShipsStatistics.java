package de.floribe2000.warships_java.direct.seasons;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.stats.RankedStatsContainer;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;
import java.util.List;
import java.util.Map;
import lombok.Getter;

/**
 * A representation of the api result of ranked battles ships statistics. This class holds all data
 * returned by this request to allow easy access to this information
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
	 * Details about errors in case of a failed request.
	 * <p>Field is null if no errors occurred during the request!</p>
	 */
	private ErrorContainer error = null;

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

			private static RankedStatsContainer rank_div3;

			private static RankedStatsContainer rank_solo;

			private static RankedStatsContainer rank_div2;

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
