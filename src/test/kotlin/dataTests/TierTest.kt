package dataTests

import de.floribe2000.warships_java.direct.api.typeDefinitions.Tier
import org.junit.jupiter.api.Test
import utilities.ITestClass

class TierTest : ITestClass {
    override val instanceName: String = "TEST"

    @Test
    fun tierRange_AllTiersIncluded() {
        val range = Tier.I..Tier.X

        for (tier in Tier.values()) {
            assert(tier in range) { tier }
        }
        assert(range.count() == Tier.values().size)
    }

    @Test
    fun tierRange_3TierRange() {
        val range = Tier.VII..Tier.IX

        assert(range.count() == 3)
        assert(range.contains(Tier.VII))
        assert(range.contains(Tier.VIII))
        assert(range.contains(Tier.IX))
    }

    @Test
    fun tierRange_StartEndEqual() {
        val range = Tier.IX..Tier.IX

        assert(range.count() == 1)
        assert(Tier.IX in range)
        assert(Tier.X !in range)
        assert(Tier.VIII !in range)
    }
}