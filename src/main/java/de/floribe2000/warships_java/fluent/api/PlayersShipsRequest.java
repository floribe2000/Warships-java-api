package de.floribe2000.warships_java.fluent.api;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayersShipsRequest implements Request, Queryable<String> {

	protected Set<Long> shipIds = new HashSet<>();
	protected PlayerRequest parent;

	protected PlayersShipsRequest(PlayerRequest parent, long shipId) {
		shipIds.add(shipId);
		this.parent = parent;
	}

	protected PlayersShipsRequest(PlayerRequest parent, Long... shipIds) {
		Collections.addAll(this.shipIds, shipIds);
		this.parent = parent;
	}
	protected PlayersShipsRequest(PlayerRequest parent, Collection<Long> shipIds) {
		shipIds.addAll(shipIds);
		this.parent = parent;
	}

	@Override
	public String query() {
		StringBuilder builder = new StringBuilder(parent.api.region.getBaseURL()).append(SHIP_STATS)
			.append("/?application_id=").append(parent.api.applicationId)
			.append("&account_id=").append(parent.accountId)
			.append("&ship_id=").append(shipIds.stream().map(l -> Long.toString(l))
				.collect(Collectors.joining(URL_COMMA_CONCAT)));

		if (!parent.modes.isEmpty()) {
			if (parent.modes.contains(GameMode.RANDOM)) {
				// do not exclude default field
			} else {
				builder.append("&fields=-").append(GameMode.RANDOM.getRequestName());
			}
			builder.append("&extra=").append(parent.modes.stream().filter(mode -> !mode.equals(GameMode.RANDOM)).map(m -> m.getRequestName())
				.collect(Collectors.joining(URL_COMMA_CONCAT)));
		}

		// TODO execute and change return type to POJO
		return builder.toString();
	}
}
