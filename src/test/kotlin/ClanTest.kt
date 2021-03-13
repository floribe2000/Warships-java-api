import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import de.floribe2000.warships_java.direct.api.typeDefinitions.Tier
import de.floribe2000.warships_java.direct.clans.ClanBattleSeasonsRequest
import org.junit.jupiter.api.Test
import utilities.ITestClass
import java.io.FileInputStream
import java.util.*

class ClanTest : ITestClass {
    override val instanceName: String = "TEST"

    @Test
    fun clanBattleSeasonsRequest_successfulTest() {
        setupApi()
        val request = ClanBattleSeasonsRequest().region(Region.EU)

        val cbSeasons = request.fetch()

        assert(cbSeasons.status.get()) { cbSeasons }
        assert(cbSeasons.data.containsKey(11)) { cbSeasons }
        assert(cbSeasons.data[11]?.maxTier == Tier.X)
    }

    @Test
    fun clanBattleSeasonsRequest_containsNoUnexpectedSeason() {
        setupApi()
        val request = ClanBattleSeasonsRequest().region(Region.EU)

        val cbSeasons = request.fetch()

        assert(cbSeasons.status.get()) { cbSeasons }
        assert(!cbSeasons.data.containsKey(-1)) { cbSeasons }
    }

}