package de.floribe2000.warships_java.direct.warships;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.stats.ExtendedWeaponStatsWithShip;
import de.floribe2000.warships_java.direct.api.stats.OperationStatsContainer;
import de.floribe2000.warships_java.direct.api.stats.ShipStatsContainer;
import de.floribe2000.warships_java.direct.api.stats.WeaponStatsWithShip;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class Statistics implements IApiResponse {

	private Status status = Status.ERROR;

	/**
	 * Details about errors in case of a failed request.
	 * <p>Field is null if no errors occurred during the request!</p>
	 */
	private ErrorContainer error = null;

	private Meta meta = null;

	private Map<Long, List<ShipEntry>> data = null;

	@Getter
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
			return IRequestAction.GSON.toJson(this);
		}

	}

	@Override
	public String toString() {
		return IRequestAction.GSON.toJson(this);
	}
}
