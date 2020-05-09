import de.floribe2000.warships_java.direct.api.stats.ExtendedWeaponStatsWithShip;
import de.floribe2000.warships_java.direct.api.stats.ExtendedWeaponStats;
import de.floribe2000.warships_java.direct.api.stats.WeaponStats;
import de.floribe2000.warships_java.direct.api.stats.WeaponStatsWithShip;
import de.floribe2000.warships_java.direct.api.stats.implementation.ExtendedWeaponStatsWithShipImpl;
import de.floribe2000.warships_java.direct.api.stats.implementation.ExtendedWeaponStatsImpl;
import de.floribe2000.warships_java.direct.api.stats.implementation.WeaponStatsImpl;
import de.floribe2000.warships_java.direct.api.stats.implementation.WeaponStatsWithShipImpl;
import java.util.Collection;
import java.util.HashSet;
import org.junit.Test;

public class StatsContainerTest {

	@Test
	public void testAccessibility() {
		WeaponStatsWithShipImpl weaponStats = new WeaponStatsWithShipImpl();
		WeaponStatsImpl shipWeaponStats = new WeaponStatsImpl();
		ExtendedWeaponStatsWithShipImpl extendedWeaponStats = new ExtendedWeaponStatsWithShipImpl();
		ExtendedWeaponStatsImpl shipExtendedWeaponStats = new ExtendedWeaponStatsImpl();

		Collection<WeaponStats> base = new HashSet<>();
		base.add(weaponStats);
		base.add(shipWeaponStats);
		base.add(extendedWeaponStats);
		base.add(shipExtendedWeaponStats);

		Collection<WeaponStatsWithShip> baseWithShipId = new HashSet<>();
		baseWithShipId.add(weaponStats);
		baseWithShipId.add(extendedWeaponStats);

		Collection<ExtendedWeaponStats> extended = new HashSet<>();
		extended.add(shipExtendedWeaponStats);
		extended.add(extendedWeaponStats);

		Collection<ExtendedWeaponStatsWithShip> full = new HashSet<>();
		full.add(extendedWeaponStats);
	}

}
