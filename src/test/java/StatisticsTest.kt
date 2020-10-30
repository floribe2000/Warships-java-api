import de.floribe2000.warships_java.direct.api.ApiBuilder
import de.floribe2000.warships_java.direct.api.typeDefinitions.*
import de.floribe2000.warships_java.direct.warships.StatisticsRequest
import org.junit.Assert
import org.junit.Test
import java.io.FileInputStream
import java.util.*

class StatisticsTest {
    private val apiKey: String
    private val instanceName = "TEST"

    private val wargamingId: Long = 540241530
    private val kii = 3762239184L

    @Test
    fun testShipStats() {
        ApiBuilder.createInstance(apiKey, instanceName)
        val request = StatisticsRequest.createRequest()
                .accountId(wargamingId).region(Region.EU).addShipId(kii)
        val response = request.fetch()
        Assert.assertNotNull(response)
        Assert.assertEquals(Status.OK, response.status)
        val ships = response.data?.get(wargamingId)
        Assert.assertNotNull(ships)
        Assert.assertFalse(ships?.isEmpty() ?: false)
        Assert.assertEquals(kii, ships?.get(0)?.shipId)
        Assert.assertNotNull(ships?.get(0)?.pvp)
        Assert.assertNull(ships?.get(0)?.pve)
    }

    @Test
    fun testShipStatsFiltered() {
        ApiBuilder.createInstance(apiKey, instanceName)
        val request = StatisticsRequest.createRequest()
                .accountId(wargamingId).region(Region.EU)
                .shipTypes(ShipType.CRUISER)
                .tiers(Tier.VIII, Tier.IX, Tier.X)
                .nations(Nation.USA)
                .categories(ShipCategory.RESEARCH)
        val response = request.fetch()
        Assert.assertNotNull(response)
        assert(response.status.get()) { response }
        //assertEquals(OK, response.getStatus());
        val ships = response.data?.get(wargamingId)
        Assert.assertNotNull(ships)
        assert(ships?.isNotEmpty() ?: false) { ships ?: "Error" }

        // So far, there are 6 researchable US navy cruisers on tier VIII - X (CLs and CAs)
        // But actually I never player the CA VIII and IX
        Assert.assertTrue(ships?.size ?: -1 >= 4)
        Assert.assertNotNull(ships?.get(0)?.pvp)
    }

    init {
        val properties = Properties()
        properties.load(FileInputStream("Warships.properties"))
        apiKey = properties.getProperty("APIKEY")
    }
}