package de.floribe2000.warships_java.direct.api.stats;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import lombok.Getter;

@Getter
public class ExtendedWeaponStatsWithShip extends WeaponStatsWithShip implements IExtendedWeaponStats {

	public static ExtendedWeaponStatsWithShip mergeExtendedWithShip(Collection<ExtendedWeaponStatsWithShip> stats) {
		ExtendedWeaponStatsWithShip merged = new ExtendedWeaponStatsWithShip();
		stats.stream().filter(Objects::nonNull).forEach(s -> {
			merged.hits += s.hits;
			merged.shots += s.shots;
		});
		return merged;
	}

	public static ExtendedWeaponStatsWithShip mergeExtendedWithShip(ExtendedWeaponStatsWithShip... stats) {
		return mergeExtendedWithShip(Arrays.asList(stats));
	}

	protected int hits = 0;
	protected int shots = 0;

}
