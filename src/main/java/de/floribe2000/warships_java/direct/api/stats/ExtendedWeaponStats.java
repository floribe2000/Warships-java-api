package de.floribe2000.warships_java.direct.api.stats;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import lombok.Getter;

/**
 * Extended weapon stats container for weapon types that define shots and hits by this type
 *
 * @author SirLefti
 */
@Getter
public class ExtendedWeaponStats extends WeaponStats implements IExtendedWeaponStats {

	public static ExtendedWeaponStats mergeExtended(Collection<ExtendedWeaponStats> stats) {
		ExtendedWeaponStats merged = new ExtendedWeaponStats();
		stats.stream().filter(Objects::nonNull).forEach(s -> {
			merged.hits += s.hits;
			merged.shots += s.shots;
		});
		return merged;
	}

	public static ExtendedWeaponStats mergeExtended(ExtendedWeaponStats... stats) {
		return mergeExtended(Arrays.asList(stats));
	}

	protected int hits = 0;
	protected int shots = 0;
}
