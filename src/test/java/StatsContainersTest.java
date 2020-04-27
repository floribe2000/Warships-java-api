import de.floribe2000.warships_java.direct.api.stats.ExtendedWeaponStats;
import de.floribe2000.warships_java.direct.api.stats.ExtendedWeaponStatsWithShip;
import de.floribe2000.warships_java.direct.api.stats.IExtendedWeaponStats;
import de.floribe2000.warships_java.direct.api.stats.IWeaponStats;
import de.floribe2000.warships_java.direct.api.stats.WeaponStats;
import de.floribe2000.warships_java.direct.api.stats.WeaponStatsWithShip;
import java.util.Collection;
import java.util.HashSet;
import org.junit.Test;

public class StatsContainersTest {

	@Test
	public void testAccessability() {
		// technically, this interface is not necessarily needed
		Collection<IWeaponStats> baseStats = new HashSet<>();
		// everything is allowed
		baseStats.add(new WeaponStats());
		baseStats.add(new WeaponStatsWithShip());
		baseStats.add(new ExtendedWeaponStats());
		baseStats.add(new ExtendedWeaponStatsWithShip());

		Collection<IExtendedWeaponStats> extendedStats = new HashSet<>();
		extendedStats.add(new ExtendedWeaponStats());
		extendedStats.add(new ExtendedWeaponStatsWithShip());
		// not allowed
//		extendedStats.add(new WeaponStatsWithShip());
//		extendedStats.add(new WeaponStats());

		Collection<WeaponStats> withoutShip = new HashSet<>();
		withoutShip.add(new WeaponStats());
		withoutShip.add(new ExtendedWeaponStats());
		// allowed but not accessible
		withoutShip.add(new WeaponStatsWithShip());
		withoutShip.add(new ExtendedWeaponStatsWithShip());


		Collection<WeaponStatsWithShip> withShip = new HashSet<>();
		withShip.add(new WeaponStatsWithShip());
		withShip.add(new ExtendedWeaponStatsWithShip());
		// not allowed
//		withShip.add(new WeaponStats());
//		withShip.add(new ExtendedWeaponStats());

	}

}
