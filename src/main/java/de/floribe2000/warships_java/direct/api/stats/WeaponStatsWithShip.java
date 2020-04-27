package de.floribe2000.warships_java.direct.api.stats;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import lombok.Getter;

/**
 * Base weapon stats container holding also the shipId of highest scoring ship
 *
 * @author SirLefti
 */
@Getter
public class WeaponStatsWithShip extends WeaponStats {

	public static WeaponStatsWithShip mergeWithShip(Collection<WeaponStatsWithShip> stats) {
		WeaponStatsWithShip merged = new WeaponStatsWithShip();
		stats.stream().filter(Objects::nonNull).forEach(s -> merged.frags += s.frags);
		stats.stream().filter(Objects::nonNull).max(Comparator.comparingInt(WeaponStats::getMax_frags_battle)).ifPresent(s -> {
			merged.max_frags_battle = s.max_frags_battle;
			merged.max_frags_ship_id = s.max_frags_ship_id;
		});
		return merged;
	}

	public static WeaponStats mergeWithShip(WeaponStatsWithShip... stats) {
		return mergeWithShip(Arrays.asList(stats));
	}

	protected long max_frags_ship_id = 0;

}
