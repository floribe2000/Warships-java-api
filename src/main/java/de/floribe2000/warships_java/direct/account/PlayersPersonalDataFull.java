package de.floribe2000.warships_java.direct.account;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.stats.OperationStatsContainer;
import de.floribe2000.warships_java.direct.api.stats.PlayerStatsContainer;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;

import java.util.Map;

/**
 * A representation of a full data set from the api. Contains all available fields for player and
 * player pvp stats.
 */
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

    public Status getStatus() {
        return this.status;
    }

    public ErrorContainer getError() {
        return this.error;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public Map<String, PlayerDetails> getData() {
        return this.data;
    }

    /**
     * A representation of individual player statistics.
     */
    public static class PlayerDetails {

        private int last_battle_time = 0;

        private long account_id = 0;

        private int leveling_tier = 0;

        private int created_at = 0;

        private boolean hidden_profile = false;

        private int logout_at = 0;

        private Statistics statistics = null;

        public int getLast_battle_time() {
            return this.last_battle_time;
        }

        public long getAccount_id() {
            return this.account_id;
        }

        public int getLeveling_tier() {
            return this.leveling_tier;
        }

        public int getCreated_at() {
            return this.created_at;
        }

        public boolean isHidden_profile() {
            return this.hidden_profile;
        }

        public int getLogout_at() {
            return this.logout_at;
        }

        public Statistics getStatistics() {
            return this.statistics;
        }

        public String getNickname() {
            return this.nickname;
        }

        public long getStats_updated_at() {
            return this.stats_updated_at;
        }

        /**
         * A representation of the player's battle statistics
         */
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

            public int getDistance() {
                return this.distance;
            }

            public int getBattles() {
                return this.battles;
            }

            public PlayerStatsContainer getPvp() {
                return this.pvp;
            }

            public PlayerStatsContainer getPve() {
                return this.pve;
            }

            public PlayerStatsContainer getPvp_div2() {
                return this.pvp_div2;
            }

            public PlayerStatsContainer getPvp_div3() {
                return this.pvp_div3;
            }

            public PlayerStatsContainer getPve_div2() {
                return this.pve_div2;
            }

            public PlayerStatsContainer getPve_div3() {
                return this.pve_div3;
            }

            public PlayerStatsContainer getRank_solo() {
                return this.rank_solo;
            }

            public PlayerStatsContainer getRank_div2() {
                return this.rank_div2;
            }

            public PlayerStatsContainer getRank_div3() {
                return this.rank_div3;
            }

            public OperationStatsContainer getOper_solo() {
                return this.oper_solo;
            }

            public OperationStatsContainer getOper_div() {
                return this.oper_div;
            }

            public OperationStatsContainer getOper_div_hard() {
                return this.oper_div_hard;
            }
		}

		private String nickname = null;

		private long stats_updated_at = 0;
	}

	@Override
	public String toString() {
		return IRequestAction.GSON.toJson(this);
	}
}
