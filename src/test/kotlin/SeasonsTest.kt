import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status
import de.floribe2000.warships_java.direct.seasons.RankedBattlesPlayerStatisticsRequest
import de.floribe2000.warships_java.direct.seasons.RankedBattlesSeasonsRequest
import de.floribe2000.warships_java.direct.seasons.RankedBattlesShipsStatisticsRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import utilities.ITestClass

class SeasonsTest : ITestClass {
    private val accountId: Long = 540241530
    override val instanceName = "TEST"

    @Test
    @Disabled("Seasons data was disabled by WG.")
    fun testSeasonsRequest() {
        setupApi()
        val request = RankedBattlesSeasonsRequest.createRequest()
            .region(Region.EU).addSeason(15)
        val result = request.fetch()
        assertEquals(Status.OK, result.status)
    }

    @Test
    fun testSeasonsPlayerStats() {
        setupApi()
        val request = RankedBattlesPlayerStatisticsRequest().region(Region.EU).addSeason(15).addAccountId(accountId)
        val result = request.fetch()
        assertEquals(Status.OK, result.status)
    }

    @Test
    fun testSeasonsShipStats() {
        setupApi()
        val request = RankedBattlesShipsStatisticsRequest()
            .region(Region.EU)
            .addSeason(15)
            .addAccountId(accountId)
        val result = request.fetch()
        assertEquals(Status.OK, result.status)
    }
}