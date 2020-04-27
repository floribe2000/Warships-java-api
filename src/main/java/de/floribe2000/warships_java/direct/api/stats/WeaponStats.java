package de.floribe2000.warships_java.direct.api.stats;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import lombok.Getter;

/**
 * Base weapon stats container for weapon types that do not define shots and hits by this type
 *
 * @author SirLefti
 */
@Getter
public class WeaponStats implements IWeaponStats {

	public static WeaponStats merge(Collection<WeaponStats> stats) {
		WeaponStats merged = new WeaponStats();
		stats.stream().filter(Objects::nonNull).forEach(s -> merged.frags += s.frags);
		stats.stream().filter(Objects::nonNull).max(Comparator.comparingInt(WeaponStats::getMax_frags_battle)).ifPresent(s -> {
			merged.max_frags_battle = s.max_frags_battle;
		});
		return merged;
	}

	public static WeaponStats merge(ExtendedWeaponStats... stats) {
		return merge(Arrays.asList(stats));
	}

	protected int frags = 0;
	protected int max_frags_battle = 0;

}
