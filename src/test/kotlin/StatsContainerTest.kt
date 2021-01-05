import de.floribe2000.warships_java.direct.api.stats.ExtendedWeaponStats
import de.floribe2000.warships_java.direct.api.stats.ExtendedWeaponStatsWithShip
import de.floribe2000.warships_java.direct.api.stats.WeaponStats
import de.floribe2000.warships_java.direct.api.stats.WeaponStatsWithShip
import de.floribe2000.warships_java.direct.api.stats.implementation.ExtendedWeaponStatsImpl
import de.floribe2000.warships_java.direct.api.stats.implementation.ExtendedWeaponStatsWithShipImpl
import de.floribe2000.warships_java.direct.api.stats.implementation.WeaponStatsImpl
import de.floribe2000.warships_java.direct.api.stats.implementation.WeaponStatsWithShipImpl
import org.junit.jupiter.api.Test
import java.util.*

class StatsContainerTest {
    @Test
    fun testAccessibility() {
        val weaponStats = WeaponStatsWithShipImpl()
        val shipWeaponStats = WeaponStatsImpl()
        val extendedWeaponStats = ExtendedWeaponStatsWithShipImpl()
        val shipExtendedWeaponStats = ExtendedWeaponStatsImpl()
        val base: MutableCollection<WeaponStats> = HashSet()
        base.add(weaponStats)
        base.add(shipWeaponStats)
        base.add(extendedWeaponStats)
        base.add(shipExtendedWeaponStats)
        val baseWithShipId: MutableCollection<WeaponStatsWithShip> = HashSet()
        baseWithShipId.add(weaponStats)
        baseWithShipId.add(extendedWeaponStats)
        val extended: MutableCollection<ExtendedWeaponStats> = HashSet()
        extended.add(shipExtendedWeaponStats)
//        extended.add(extendedWeaponStats)
        val full: MutableCollection<ExtendedWeaponStatsWithShip> = HashSet()
        full.add(extendedWeaponStats)
    }
}