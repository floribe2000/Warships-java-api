package de.floribe2000.warships_java.fluent.api;

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

public class PlayerRequest implements Request, LanguageSelectable<PlayerRequest>,
	FieldsSelectable<PlayerRequest>, ExtraSelectable<PlayerRequest>,
	GameModeSelectable<PlayerRequest>, ShipSelectable<PlayersShipsRequest>,
	PlayerSelectable<PlayerRequest>, Queryable<String> {

	protected BaseRequest request;
	protected long accountId;
	protected String language;
	protected Set<GameMode> modes = new HashSet<>();
	protected Set<String> fields = new HashSet<>();
	protected Set<String> extras = new HashSet<>();

	protected PlayerRequest(BaseRequest request, long accountId) {
		this.request = request;
		this.accountId = accountId;
	}

	@Override
	public PlayerRequest ofPlayer(long accountId) {
		this.accountId = accountId;
		return this;
	}

	@Override
	public PlayersShipsRequest ofShip(long shipId) {
		return new PlayersShipsRequest(this, shipId);
	}

	@Override
	public PlayersShipsRequest ofShips(Long... shipIds) {
		return new PlayersShipsRequest(this, shipIds);
	}

	@Override
	public PlayersShipsRequest ofShips(Collection<Long> shipIds) {
		return new PlayersShipsRequest(this, shipIds);
	}

	@Override
	public PlayerRequest ofGameMode(GameMode mode) {
		this.modes.add(mode);
		return this;
	}

	@Override
	public PlayerRequest ofGameModes(GameMode... modes) {
		Collections.addAll(this.modes, modes);
		return this;
	}

	@Override
	public PlayerRequest ofGameModes(Collection<GameMode> modes) {
		this.modes.addAll(modes);
		return this;
	}

	@Override
	public PlayerRequest clearGameModes() {
		this.modes.clear();
		return this;
	}

	@Override
	public PlayerRequest withExtra(String extra) {
		extras.add(extra);
		return this;
	}

	@Override
	public PlayerRequest withExtras(String... extras) {
		Collections.addAll(this.extras, extras);
		return this;
	}

	@Override
	public PlayerRequest withExtras(Collection<String> extras) {
		this.extras.addAll(extras);
		return this;
	}

	@Override
	public PlayerRequest clearExtras() {
		this.extras.clear();
		return this;
	}

	@Override
	public PlayerRequest withField(String field) {
		this.fields.add(field);
		return this;
	}

	@Override
	public PlayerRequest withFields(String... fields) {
		Collections.addAll(this.fields, fields);
		return this;
	}

	@Override
	public PlayerRequest withFields(Collection<String> fields) {
		this.fields.addAll(fields);
		return this;
	}

	@Override
	public PlayerRequest clearFields() {
		this.fields.clear();
		return this;
	}

	@Override
	public PlayerRequest withLanguage(String language) {
		this.language = language;
		return this;
	}

	@Override
	public PlayerRequest clearLanguage() {
		this.language = null;
		return this;
	}

	public String query() {
		// build base request string: base URL + path + applicationId + accountId
		StringBuilder builder = new StringBuilder(request.region.getBaseURL()).append(PLAYERS_STATS)
			.append("/?application_id=").append(request.applicationId)
			.append("&account_id=").append(accountId);

		/* If modes contains some values but not RANDOM we exclude it in the request
		 * Since we are using a set we can bulk-add this even if the user provided this value
		 * on its own
		 */
		if (modes.size() > 0 && !modes.contains(GameMode.RANDOM)) {
			fields.add("-statistics." + GameMode.RANDOM.getRequestName());
		}

		/* Now we fill the extra fields with selected games modes excluding RANDOM
		 * Since we are using a set we can bulk-add those even if the user provided some of those
		 * values on its own
		 */
		modes.stream().filter(m -> !m.equals(GameMode.RANDOM))
			.map(m -> "statistics." + m.getRequestName())
			.sequential()
			.collect(Collectors.toCollection(() -> extras));

		// Now we build the extra and fields URL parameter and optionally the language parameter
		String extraParam = extras.isEmpty() ? "" : "&extra=" + extras.stream().sequential().collect(Collectors.joining(URL_COMMA_CONCAT));
		String fieldsParam = fields.isEmpty() ? "" : "&fields=" + fields.stream().sequential().collect(Collectors.joining(URL_COMMA_CONCAT));
		String languageParam = language == null ? "" : "&language=" + language;

		// Now we can merge all together
		builder.append(languageParam).append(fieldsParam).append(extraParam);

		// TODO execute and change return type to POJO
		return builder.toString();
	}
}
