package de.floribe2000.warships_java.direct.seasons;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;

import java.util.Map;

/**
 * A representation of the api result of ranked battles seasons.
 * This class holds all data returned by this request to allow easy access to this information
 *
 * @author SirLefti
 */
public class RankedBattlesSeasons implements IApiResponse {

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
    private Map<Integer, SeasonElement> data;

    public Status getStatus() {
        return this.status;
    }

    public ErrorContainer getError() {
        return this.error;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public Map<Integer, SeasonElement> getData() {
        return this.data;
    }

    /**
     * A class representing a single season returned by the api
     */
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

        public int getAccount_tier() {
            return this.account_tier;
        }

        public int getMax_ship_tier() {
            return this.max_ship_tier;
        }

        public long getStart_at() {
            return this.start_at;
        }

        public String getSeason_name() {
            return this.season_name;
        }

        public long getFinish_at() {
            return this.finish_at;
        }

        public int getSeason_id() {
            return this.season_id;
        }

        public int getMin_ship_tier() {
            return this.min_ship_tier;
        }

        public int getParent_season_id() {
            return this.parent_season_id;
        }

        public int getStart_rank() {
            return this.start_rank;
        }

        public long getClose_at() {
            return this.close_at;
        }

        public Map<Integer, ImageElement> getImages() {
            return this.images;
        }

        public static class ImageElement {

            /**
             * URL for rank insignia image
             */
            private String insignia;

            /**
             * URL for rank background image
             */
            private String background;

            public String getInsignia() {
                return this.insignia;
            }

            public String getBackground() {
                return this.background;
			}
		}
	}

	@Override
	public String toString() {
        return IRequestAction.Companion.getGSON().toJson(this);
    }
}
