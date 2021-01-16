import de.floribe2000.warships_java.direct.api.typeDefinitions.*
import de.floribe2000.warships_java.direct.warships.StatisticsRequest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import utilities.ITestClass
import java.io.FileInputStream
import java.util.*

class StatisticsTest : ITestClass {
    override val apiKey: String
    override val instanceName = "TEST"

    private val wargamingId: Long = 540241530
    private val kii = 3762239184L

    @Test
    fun testShipStats() {
        setupApi()
        val request = StatisticsRequest.createRequest()
            .accountId(wargamingId).region(Region.EU).addShipId(kii)
        val response = request.fetch()
        assertNotNull(response)
        assertEquals(Status.OK, response.status)
        val ships = response.data?.get(wargamingId)
        assertNotNull(ships)
        assertFalse(ships?.isEmpty() ?: false)
        assertEquals(kii, ships?.get(0)?.shipId)
        assertNotNull(ships?.get(0)?.pvp)
        assertNull(ships?.get(0)?.pve)
    }

    @Test
    fun testShipStatsFiltered() {
        setupApi()
        val request = StatisticsRequest.createRequest()
                .accountId(wargamingId).region(Region.EU)
                .shipTypes(ShipType.CRUISER)
                .tiers(Tier.VIII, Tier.IX, Tier.X)
                .nations(Nation.USA)
                .categories(ShipCategory.RESEARCH)
        val response = request.fetch()
        assertNotNull(response)
        assert(response.status.get()) { response }
        //assertEquals(OK, response.getStatus());
        val ships = response.data?.get(wargamingId)
        assertNotNull(ships)
        assert(ships?.isNotEmpty() ?: false) { ships ?: "Error" }

        // So far, there are 6 researchable US navy cruisers on tier VIII - X (CLs and CAs)
        // But actually I never player the CA VIII and IX
        assertTrue(ships?.size ?: -1 >= 4)
        assertNotNull(ships?.get(0)?.pvp)
    }

    init {
        val properties = Properties()
        properties.load(FileInputStream("Warships.properties"))
        apiKey = properties.getProperty("APIKEY")
    }
}