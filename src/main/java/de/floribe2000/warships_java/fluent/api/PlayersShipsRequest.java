package de.floribe2000.warships_java.fluent.api;

import de.floribe2000.warships_java.fluent.api.selectors.ClearableShipSelectable;
import de.floribe2000.warships_java.fluent.api.selectors.ExtraSelectable;
import de.floribe2000.warships_java.fluent.api.selectors.FieldsSelectable;
import de.floribe2000.warships_java.fluent.api.selectors.GameModeSelectable;
import de.floribe2000.warships_java.fluent.api.selectors.LanguageSelectable;
import de.floribe2000.warships_java.fluent.api.selectors.PlayerSelectable;
import de.floribe2000.warships_java.fluent.api.selectors.ShipSelectable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayersShipsRequest implements Request, LanguageSelectable<PlayersShipsRequest>,
	FieldsSelectable<PlayersShipsRequest>, ExtraSelectable<PlayersShipsRequest>,
	GameModeSelectable<PlayersShipsRequest>, ShipSelectable<PlayersShipsRequest>,
	ClearableShipSelectable<PlayerRequest>, PlayerSelectable<PlayersShipsRequest>,
	Queryable<String> {

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
		this.shipIds.addAll(shipIds);
		this.parent = parent;
	}

	@Override
	public PlayersShipsRequest ofPlayer(long accountId) {
		parent.accountId = accountId;
		return this;
	}

	@Override
	public PlayersShipsRequest withExtra(String extra) {
		parent.extras.add(extra);
		return this;
	}

	@Override
	public PlayersShipsRequest withExtras(String... extras) {
		Collections.addAll(parent.extras, extras);
		return this;
	}

	@Override
	public PlayersShipsRequest withExtras(Collection<String> extras) {
		parent.extras.addAll(extras);
		return this;
	}

	@Override
	public PlayersShipsRequest clearExtras() {
		parent.extras.clear();
		return this;
	}

	@Override
	public PlayersShipsRequest withField(String field) {
		parent.fields.add(field);
		return this;
	}

	@Override
	public PlayersShipsRequest withFields(String... fields) {
		Collections.addAll(parent.fields, fields);
		return this;
	}

	@Override
	public PlayersShipsRequest withFields(Collection<String> fields) {
		parent.fields.addAll(fields);
		return this;
	}

	@Override
	public PlayersShipsRequest clearFields() {
		parent.fields.clear();
		return this;
	}

	@Override
	public PlayersShipsRequest withLanguage(String language) {
		parent.language = language;
		return this;
	}

	@Override
	public PlayersShipsRequest clearLanguage() {
		parent.language = null;
		return this;
	}

	@Override
	public PlayersShipsRequest ofShip(long shipId) {
		shipIds.add(shipId);
		return this;
	}

	@Override
	public PlayersShipsRequest ofShips(Long... shipIds) {
		Collections.addAll(this.shipIds, shipIds);
		return this;
	}

	@Override
	public PlayersShipsRequest ofShips(Collection<Long> shipIds) {
		this.shipIds.addAll(shipIds);
		return this;
	}

	@Override
	public PlayerRequest clearShips() {
		return parent;
	}

	@Override
	public PlayersShipsRequest ofGameMode(GameMode mode) {
		parent.modes.add(mode);
		return this;
	}

	@Override
	public PlayersShipsRequest ofGameModes(GameMode... modes) {
		Collections.addAll(parent.modes, modes);
		return this;
	}

	@Override
	public PlayersShipsRequest ofGameModes(Collection<GameMode> modes) {
		parent.modes.addAll(modes);
		return this;
	}

	@Override
	public PlayersShipsRequest clearGameModes() {
		parent.modes.clear();
		return this;
	}

	@Override
	public String query() {
		StringBuilder builder = new StringBuilder(parent.request.region.getBaseURL()).append(SHIP_STATS)
			.append("/?application_id=").append(parent.request.applicationId)
			.append("&account_id=").append(parent.accountId);

		/* If modes contains some values but not RANDOM we exclude it in the request
		 * Since we are using a set we can bulk-add this even if the user provided this value
		 * on its own
		 */
		if (parent.modes.size() > 0 && !parent.modes.contains(GameMode.RANDOM)) {
			parent.fields.add("-" + GameMode.RANDOM.getRequestName());
		}

		/* Now we fill the extra fields with selected games modes excluding RANDOM
		 * Since we are using a set we can bulk-add those even if the user provided some of those
		 * values on its own
		 */
		parent.modes.stream().filter(m -> !m.equals(GameMode.RANDOM))
			.map(m -> m.getRequestName())
			.sequential()
			.collect(Collectors.toCollection(() -> parent.extras));

		/* Now we build the ship parameter, it should never become empty since it would be converted
		 * to a PlayerRequest
		 */
		String shipsParam = "&ship_id=" + shipIds.stream().map(Object::toString).sequential()
			.collect(Collectors.joining(URL_COMMA_CONCAT));

		// Now we build the extra and fields URL parameter and optionally the language parameter
		String extraParam = parent.extras.isEmpty() ? "" : "&extra=" + parent.extras.stream().sequential().collect(Collectors.joining(URL_COMMA_CONCAT));
		String fieldsParam = parent.fields.isEmpty() ? "" : "&fields=" + parent.fields.stream().sequential().collect(Collectors.joining(URL_COMMA_CONCAT));
		String languageParam = parent.language == null ? "" : "&language=" + parent.language;

		// Now we can merge all together
		builder.append(languageParam).append(fieldsParam).append(extraParam).append(shipsParam);

		// TODO execute and change return type to POJO
		return builder.toString();
	}
}
