package de.floribe2000.warships_java.seasons;

import de.floribe2000.warships_java.api.IApiResponse;
import de.floribe2000.warships_java.api.IRequestAction;
import de.floribe2000.warships_java.api.Meta;

import java.util.List;
import java.util.Map;
import lombok.Getter;

/**
 * A representation of the results provided by <a href="https://api.worldofwarships.eu/wows/seasons/info/>/wows/seasons/info/</a>
 * This class holds all data returned by this request to allow easy access to this information
 *
 * @author SirLefti
 */
@Getter
public class RankedBattlesSeasons implements IApiResponse {

	/**
	 * Response status
	 */
	private String status;

	/**
	 * Response meta data
	 */
	private Meta meta;

	/**
	 * Map containing request objects
	 */
	private Map<Integer, SeasonElement> data;

	/**
	 * A class representing a single season returned by the api
	 */
	@Getter
	public static class SeasonElement {

		/**
		 * required account tier
		 */
		private int account_tier;

		/**
		 * max battle tier
		 */
		private int max_ship_tier;

		/**
		 * season start timestamp
		 */
		private long start_at;

		/**
		 * seasons name
		 */
		private String season_name;

		/**
		 * season end timestamp
		 */
		private long finish_at;

		/**
		 * seasons id
		 */
		private int season_id;

		/**
		 * min battle tier
		 */
		private int min_ship_tier;

		/**
		 * parent season
		 */
		private int parent_season_id;

		/**
		 * start rank
		 */
		private int start_rank;

		/**
		 * close timestamp
		 */
		private long close_at;

		/**
		 * rank badges image URLs
		 */
		private Map<Integer, ImageElement> images;

		@Getter
		public static class ImageElement {

			/**
			 * URL for rank insignia image
			 */
			private String insignia;

			/**
			 * URL for rank background image
			 */
			private String background;
		}
	}

	@Override
	public String toString() {
		return IRequestAction.GSON.toJson(this);
	}
}
