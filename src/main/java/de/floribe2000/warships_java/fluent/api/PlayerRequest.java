package de.floribe2000.warships_java.fluent.api;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayerRequest implements Request, Queryable<String> {

	protected BaseRequest api;
	protected long accountId;
	protected Set<GameMode> modes = new HashSet<>();

	protected PlayerRequest(BaseRequest api, long accountId) {
		this.api = api;
		this.accountId = accountId;
	}

	public PlayersShipsRequest ofShip(long shipId) {
		return new PlayersShipsRequest(this, shipId);
	}

	public PlayersShipsRequest ofShips(Long... shipIds) {
		return new PlayersShipsRequest(this, shipIds);
	}

	public PlayersShipsRequest ofShips(Collection<Long> shipIds) {
		return new PlayersShipsRequest(this, shipIds);
	}

	public PlayerRequest ofGameMode(GameMode mode) {
		this.modes.add(mode);
		return this;
	}

	public PlayerRequest ofGameModes(GameMode... modes) {
		Collections.addAll(this.modes, modes);
		return this;
	}

	public PlayerRequest ofGameModes(Collection<GameMode> modes) {
		this.modes.addAll(modes);
		return this;
	}

	public String query() {
		StringBuilder builder = new StringBuilder(api.region.getBaseURL()).append(PLAYERS_STATS)
			.append("/?application_id=").append(api.applicationId)
			.append("&account_id=").append(accountId);

		if (!modes.isEmpty()) {
			if (modes.contains(GameMode.RANDOM)) {
				// do not exclude default field
			} else {
				builder.append("&fields=-").append("statistics.").append(GameMode.RANDOM.getRequestName());
			}
			builder.append("&extra=").append(modes.stream().filter(mode -> !mode.equals(GameMode.RANDOM)).map(m -> "statistics." + m.getRequestName())
				.collect(Collectors.joining(URL_COMMA_CONCAT)));
		}

		// TODO execute and change return type to POJO
		return builder.toString();
	}
}
