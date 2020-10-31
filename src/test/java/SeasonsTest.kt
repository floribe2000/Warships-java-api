import de.floribe2000.warships_java.direct.api.ApiBuilder
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status
import de.floribe2000.warships_java.direct.seasons.RankedBattlesPlayerStatisticsRequest
import de.floribe2000.warships_java.direct.seasons.RankedBattlesSeasonsRequest
import de.floribe2000.warships_java.direct.seasons.RankedBattlesShipsStatisticsRequest.Companion.createRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.FileInputStream
import java.util.*

class SeasonsTest {
    private val apiKey: String
    private val accountId: Long = 540241530
    private val instanceName = "TEST"

    @Test
    fun testSeasonsRequest() {
        ApiBuilder.createInstance(apiKey, instanceName)
        val request = RankedBattlesSeasonsRequest.createRequest()
                .region(Region.EU).addSeason(15)
        val result = request.fetch()
        println(result)
        assertEquals(Status.OK, result.status)
    }

    @Test
    fun testSeasonsPlayerStats() {
        ApiBuilder.createInstance(apiKey, instanceName)
        val request = RankedBattlesPlayerStatisticsRequest
                .createRequest().region(Region.EU).addSeason(15).addAccountId(accountId)
        val result = request.fetch()
        println(result)
        assertEquals(Status.OK, result.status)
    }

    @Test
    fun testSeasonsShipStats() {
        ApiBuilder.createInstance(apiKey, instanceName)
        val request = createRequest()
                .region(Region.EU)
                .addSeason(15)
                .addAccountId(accountId)
        val result = request.fetch()
        println(result)
        assertEquals(Status.OK, result.status)
    }

    init {
        val properties = Properties()
        properties.load(FileInputStream("Warships.properties"))
        apiKey = properties.getProperty("APIKEY")
    }
}