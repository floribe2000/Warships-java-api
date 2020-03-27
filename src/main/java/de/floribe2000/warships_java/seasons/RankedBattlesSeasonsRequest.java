package de.floribe2000.warships_java.seasons;

import de.floribe2000.warships_java.api.IRequestAction;
import de.floribe2000.warships_java.api.Language;
import de.floribe2000.warships_java.api.Region;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RankedBattlesSeasonsRequest implements IRequestAction<RankedBattlesSeasons>, IRankedBattlesSeasonsRequest<RankedBattlesSeasonsRequest> {

	/**
	 * A Logger instance used to log events of this class
	 */
	private final Logger LOG = LoggerFactory.getLogger(getClass().getSimpleName());

	/**
	 * The server region for this request
	 */
	private Region region = null;

	/**
	 * The language for the api response
	 */
	private Language language = null;

	/**
	 * The set of season IDs for this request
	 */
	private Set<Integer> seasonIds = new HashSet<>();

	@Override
	public RankedBattlesSeasonsRequest region(Region region) {
		this.region = region;
		return this;
	}

	@Override
	public RankedBattlesSeasonsRequest language(Language language) {
		this.language = language;
		return this;
	}

	public RankedBattlesSeasonsRequest seasonIds(Collection<Integer> seasonIds) {
		if (seasonIds == null || seasonIds.size() > 100 || seasonIds.size() < 1) {
			throw new IllegalArgumentException("The collection must not be null and have between 1 and 100 entries");
		}
		this.seasonIds = new HashSet<>(seasonIds);
		return this;
	}

	public RankedBattlesSeasonsRequest addSeason(int seasonId) {
		if (this.seasonIds.size() < 100) {
			this.seasonIds.add(seasonId);
		}
		return this;
	}

	@Override
	public RankedBattlesSeasons fetch() {
		return null;
	}
}
