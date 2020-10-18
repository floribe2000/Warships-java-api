package de.floribe2000.warships_java.direct.seasons;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.stats.RankedStatsContainer;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;

import java.util.Map;

/**
 * A representation of the api result of ranked battles player statistics. This class holds all data
 * returned by this request to allow easy access to this information
 *
 * @author SirLefti
 */
public class RankedBattlesPlayersStatistics implements IApiResponse {

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
    private Map<Integer, PlayersElement> data;

    public Status getStatus() {
        return this.status;
    }

    public ErrorContainer getError() {
        return this.error;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public Map<Integer, PlayersElement> getData() {
        return this.data;
    }

    public static class PlayersElement {

        /**
         * players accountId
         */
        private int account_id;

        /**
         * Map containing request objects
         */
        private Map<Integer, StatsContainer> seasons;

        public int getAccount_id() {
            return this.account_id;
        }

        public Map<Integer, StatsContainer> getSeasons() {
            return this.seasons;
        }

        public static class StatsContainer {

            /**
             * Info about season results
             */
            private static SeasonInfo rank_info;

            private static RankedStatsContainer rank_div3;

			private static RankedStatsContainer rank_solo;

			private static RankedStatsContainer rank_div2;

		}

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

            public int getMax_rank() {
                return this.max_rank;
            }

            public int getStart_rank() {
                return this.start_rank;
            }

            public int getStars() {
                return this.stars;
            }

            public int getRank() {
                return this.rank;
            }

            public int getStage() {
				return this.stage;
			}
		}
	}

}
