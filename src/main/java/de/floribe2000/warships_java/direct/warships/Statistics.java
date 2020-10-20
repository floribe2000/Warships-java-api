package de.floribe2000.warships_java.direct.warships;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.stats.OperationStatsContainer;
import de.floribe2000.warships_java.direct.api.stats.ShipStatsContainer;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;

import java.util.List;
import java.util.Map;

public class Statistics implements IApiResponse {

	private Status status = Status.ERROR;

    /**
     * Details about errors in case of a failed request.
     * <p>Field is null if no errors occurred during the request!</p>
     */
    private ErrorContainer error = null;

    private Meta meta = null;

    private Map<Long, List<ShipEntry>> data = null;

    public Status getStatus() {
        return this.status;
    }

    public ErrorContainer getError() {
        return this.error;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public Map<Long, List<ShipEntry>> getData() {
        return this.data;
    }

    public static class ShipEntry {

        private ShipStatsContainer pvp = null;

        private ShipStatsContainer pve = null;

        private ShipStatsContainer pve_div2 = null;

        private ShipStatsContainer pve_div3 = null;

		private ShipStatsContainer pve_solo = null;

		private ShipStatsContainer pvp_div2 = null;

		private ShipStatsContainer pvp_div3 = null;

		private ShipStatsContainer pvp_solo = null;

		private ShipStatsContainer rank_div2 = null;

		private ShipStatsContainer rank_div3 = null;

		private ShipStatsContainer rank_solo = null;

		private OperationStatsContainer oper_div = null;

		private OperationStatsContainer oper_div_hard = null;

		private OperationStatsContainer oper_solo = null;

		private long last_battle_time = 0;

		private int account_id = 0;

		private int distance = 0;

		private long updated_at = 0;

        private int battles = 0;

        private long ship_id = 0;

        @Override
        public String toString() {
            return IRequestAction.Companion.getGSON().toJson(this);
        }

        public ShipStatsContainer getPvp() {
            return this.pvp;
        }

        public ShipStatsContainer getPve() {
            return this.pve;
        }

        public ShipStatsContainer getPve_div2() {
            return this.pve_div2;
        }

        public ShipStatsContainer getPve_div3() {
            return this.pve_div3;
        }

        public ShipStatsContainer getPve_solo() {
            return this.pve_solo;
        }

        public ShipStatsContainer getPvp_div2() {
            return this.pvp_div2;
        }

        public ShipStatsContainer getPvp_div3() {
            return this.pvp_div3;
        }

        public ShipStatsContainer getPvp_solo() {
            return this.pvp_solo;
        }

        public ShipStatsContainer getRank_div2() {
            return this.rank_div2;
        }

        public ShipStatsContainer getRank_div3() {
            return this.rank_div3;
        }

        public ShipStatsContainer getRank_solo() {
            return this.rank_solo;
        }

        public OperationStatsContainer getOper_div() {
            return this.oper_div;
        }

        public OperationStatsContainer getOper_div_hard() {
            return this.oper_div_hard;
        }

        public OperationStatsContainer getOper_solo() {
            return this.oper_solo;
        }

        public long getLast_battle_time() {
            return this.last_battle_time;
        }

        public int getAccount_id() {
            return this.account_id;
        }

        public int getDistance() {
            return this.distance;
        }

        public long getUpdated_at() {
            return this.updated_at;
        }

        public int getBattles() {
            return this.battles;
        }

        public long getShip_id() {
            return this.ship_id;
        }
	}

	@Override
	public String toString() {
        return IRequestAction.Companion.getGSON().toJson(this);
    }
}
