package dataTests

import de.floribe2000.warships_java.direct.api.typeDefinitions.Tier
import org.junit.jupiter.api.Test
import utilities.ITestClass
import java.io.FileInputStream
import java.util.*

class TierTest : ITestClass {
    override val apiKey: String
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

    init {
        val properties = Properties()
        properties.load(FileInputStream("Warships.properties"))
        apiKey = properties.getProperty("APIKEY")
    }
}