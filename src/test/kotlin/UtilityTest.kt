import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import de.floribe2000.warships_java.direct.encyclopedia.WarshipsRequest.Companion.createRequest
import de.floribe2000.warships_java.utilities.EncyclopediaRequestService
import de.floribe2000.warships_java.utilities.EncyclopediaRequestService.requestFullWarshipsList
import de.floribe2000.warships_java.utilities.PlayerRequestService
import de.floribe2000.warships_java.utilities.PlayerRequestService.requestPlayersPersonalData
import org.junit.jupiter.api.Test
import java.io.FileInputStream
import java.util.*

class UtilityTest {
    private val apiKey: String

    @Test
    fun testShipRequestService() {
        EncyclopediaRequestService.initialize(apiKey)
        val warships = createRequest().region(Region.EU).fetch()
        assert(warships.status.get()) { "Invalid response status" }
        val fullList = requestFullWarshipsList(Region.EU)
        assert(fullList.status.get()) { "Invalid response status from combined list" }
        assert(fullList.data!!.size == warships.meta.total) { "Size of retrieved list does not match expected size. Expected " + warships.meta.total + ", got " + fullList.data!!.size }
    }

    @Test
    fun testGermanWarshipsList() {
        EncyclopediaRequestService.initialize(apiKey)
        val warships = createRequest().region(Region.EU).fetch()
        assert(warships.status.get()) { "Invalid response status" }
        val germanList = requestFullWarshipsList(Region.EU, Language.ENGLISH)
        assert(germanList.status.get()) { germanList }
        assert(germanList.data!!.size == warships.meta.total) {
            """
     Size of retrieved list does not match expected size. Expected ${warships.meta.total}, got ${germanList.data!!.size}
     Json Response:
     $germanList
     """.trimIndent()
        }
    }

    @Test
    fun testPlayerRequestService() {
        PlayerRequestService.initialize(apiKey)
        val playerName = "floribe2000"
        val player = requestPlayersPersonalData(playerName, Region.EU)
        assert(player.status.get()) { "Invalid response status" }
        assert(player.data.isNotEmpty()) { "Empty response" }
        val playerId = player.data.keys.first()
        assert(
            (player.data[playerId]
                ?: error("")).nickname == playerName
        ) { "Result does not match search name. Expected " + playerName + ", got " + player.data[playerId]!!.nickname }
    }

    init {
        val properties = Properties()
        properties.load(FileInputStream("Warships.properties"))
        apiKey = properties.getProperty("APIKEY")
    }
}