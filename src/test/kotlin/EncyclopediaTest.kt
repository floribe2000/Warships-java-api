import de.floribe2000.warships_java.direct.api.typeDefinitions.*
import de.floribe2000.warships_java.direct.encyclopedia.ConsumablesRequest
import de.floribe2000.warships_java.direct.encyclopedia.ShipParametersRequest
import de.floribe2000.warships_java.direct.encyclopedia.Warships
import de.floribe2000.warships_java.direct.encyclopedia.WarshipsRequest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import utilities.ITestClass
import java.util.function.Consumer

class EncyclopediaTest : ITestClass {
    override val instanceName = "TEST"

    @Test
    fun testWarWarshipsRequest() {
        setupApi()
        val limit = 100
        val page = 5
        val warships = WarshipsRequest.createRequest().region(Region.EU).limit(limit)
            .pageNo(page).fetch()
        assert(warships.status.get()) { warships }
        assert(
            warships.data.size == warships.meta.count
                    && warships.data.size <= limit
        ) { warships }
    }

    @Test
    fun testShipRequestFilterClassCruisers() {
        setupApi()
        val request = WarshipsRequest.createRequest().region(Region.EU).shipType(ShipType.CRUISER)
        var response: Warships
        var pageNo = 1
        do {
            response = request.pageNo(pageNo).fetch()
            assertEquals(Status.OK, response.status)
            response.data.values.forEach(Consumer { entry: Warships.ShipEntry ->
                assertEquals(
                    ShipType.CRUISER,
                    entry.type
                )
            })
            pageNo++
        } while (response.meta.page_total >= pageNo)
    }

    @Test
    fun testShipRequestFilterClassCruisersAndBattleShips() {
        setupApi()
        val request = WarshipsRequest.createRequest().region(Region.EU).shipType(ShipType.CRUISER, ShipType.BATTLESHIP)
        var response: Warships
        var pageNo = 1
        do {
            response = request.pageNo(pageNo).fetch()
            assertNotNull(response)
            assertEquals(Status.OK, response.status)
            response.data.values.forEach(Consumer { entry: Warships.ShipEntry -> assertTrue(entry.type == ShipType.CRUISER || entry.type == ShipType.BATTLESHIP) })
            pageNo++
        } while (response.meta.page_total >= pageNo)
    }

    @Test
    fun testShipRequestFilterTierX() {
        setupApi()
        val request = WarshipsRequest.createRequest().region(Region.EU).tier(Tier.X)
        var response: Warships
        var pageNo = 1
        do {
            response = request.pageNo(pageNo).fetch()
            assertNotNull(response)
            assertEquals(Status.OK, response.status)
            response.data.values.forEach(Consumer { entry: Warships.ShipEntry -> assertEquals(Tier.X, entry.tier) })
            pageNo++
        } while (response.meta.page_total >= pageNo)
    }

    @Test
    fun testShipRequestFilterTierIAndII() {
        setupApi()
        val request = WarshipsRequest.createRequest().region(Region.EU).tier(Tier.I, Tier.II)
        var response: Warships
        var pageNo = 1
        do {
            response = request.pageNo(pageNo).fetch()
            assertNotNull(response)
            assertEquals(Status.OK, response.status)
            response.data.values.forEach(Consumer { entry: Warships.ShipEntry -> assertTrue(entry.tier == Tier.I || entry.tier == Tier.II) })
            pageNo++
        } while (response.meta.page_total >= pageNo)
    }

    @Test
    fun testShipRequestFilterNationEurope() {
        setupApi()
        val request = WarshipsRequest.createRequest().region(Region.EU).nation(Nation.EUROPE)
        var response: Warships
        var pageNo = 1
        do {
            response = request.pageNo(pageNo).fetch()
            assertNotNull(response)
            assertEquals(Status.OK, response.status)
            response.data.values.forEach(Consumer { entry: Warships.ShipEntry ->
                assertEquals(
                    Nation.EUROPE,
                    entry.nation
                )
            })
            pageNo++
        } while (response.meta.page_total >= pageNo)
    }

    @Test
    fun testShipRequestFilterNationEuropeAndPanAsia() {
        setupApi()
        val request = WarshipsRequest.createRequest().region(Region.EU).nation(Nation.EUROPE, Nation.PAN_ASIA)
        var response: Warships
        var pageNo = 1
        do {
            response = request.pageNo(pageNo).fetch()
            assertNotNull(response)
            assertEquals(Status.OK, response.status)
            response.data.values.forEach(Consumer { entry: Warships.ShipEntry -> assertTrue(entry.nation == Nation.EUROPE || entry.nation == Nation.PAN_ASIA) })
            pageNo++
        } while (response.meta.page_total >= pageNo)
    }

    @Test
    fun testShipRequestFilterTypeResearch() {
        setupApi()
        val request = WarshipsRequest.createRequest().region(Region.EU).shipCategory(ShipCategory.RESEARCH)
        var response: Warships
        var pageNo = 1
        do {
            response = request.pageNo(pageNo).fetch()
            assertNotNull(response)
            assertEquals(Status.OK, response.status)
            response.data.values.forEach(Consumer { entry: Warships.ShipEntry -> assertTrue(!entry.isPremium && !entry.isSpecial) })
            pageNo++
        } while (response.meta.page_total >= pageNo)
    }

    @Test
    fun testShipRequestFilterTypePremiumOrSpecial() {
        setupApi()
        val request = WarshipsRequest.createRequest().region(Region.EU).shipCategory(ShipCategory.PREMIUM, ShipCategory.SPECIAL)
        var response: Warships
        var pageNo = 1
        do {
            response = request.pageNo(pageNo).fetch()
            assertNotNull(response)
            assertEquals(Status.OK, response.status)
            response.data.values.forEach(Consumer { entry: Warships.ShipEntry -> assertTrue(entry.isPremium || entry.isSpecial) })
            pageNo++
        } while (response.meta.page_total >= pageNo)
    }

    @Test
    fun testConsumableRequest() {
        setupApi()
        val request = ConsumablesRequest.createRequest().region(Region.EU)
        val response = request.fetch()
        assert(response.status.get()) { response }
    }

    @Test
    fun testConsumableRequestFlags() {
        setupApi()
        val request = ConsumablesRequest.createRequest().region(Region.EU).type(ConsumableType.FLAGS)
        val response = request.fetch()
        assert(response.status.get()) { response }
        for (entry in response.data.entries) {
            assert(entry.value.type === ConsumableType.FLAGS) { entry.value }
            assert(entry.key == entry.value.consumableId.toString()) { entry }
        }
    }

    @Test
    fun testShipParameters() {
        setupApi()
        val shipId = 4179605200L
        val request = ShipParametersRequest.createRequest().region(Region.EU).shipId(shipId)
        val response = request.fetch()
        assert(response.status.get()) { response }
        assert(response.data[shipId.toString()] != null) { response }
    }
}