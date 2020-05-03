package de.floribe2000.warships_java.direct.api.stats;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import lombok.Getter;

/**
 * Extended weapon stats container for all weapon types that count hits and shots.
 *
 * @author SirLefti
 */
@Getter
public class ExtendedWeaponStats extends WeaponStats {

	public static ExtendedWeaponStats mergeExtended(Collection<ExtendedWeaponStats> stats) {
		ExtendedWeaponStats merged = new ExtendedWeaponStats();

		stats.stream().filter(Objects::nonNull).forEach(s -> {
			merged.hits += s.hits;
			merged.shots += s.shots;
			merged.frags += s.frags;
		});
		stats.stream().filter(Objects::nonNull).max(Comparator.comparingInt(WeaponStats::getMax_frags_battle)).ifPresent(s -> {
			merged.max_frags_battle = s.max_frags_battle;
			merged.max_frags_ship_id = s.max_frags_ship_id;
		});
		return merged;
	}

	public static ExtendedWeaponStats mergeExtended(ExtendedWeaponStats... stats) {
		return mergeExtended(Arrays.asList(stats));
	}

	protected int hits = 0;
	protected int shots = 0;

}
