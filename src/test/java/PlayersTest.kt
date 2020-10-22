import de.floribe2000.warships_java.direct.account.PlayerStatisticsByDateRequest
import de.floribe2000.warships_java.direct.account.Players
import de.floribe2000.warships_java.direct.api.ApiBuilder.Companion.createInstance
import de.floribe2000.warships_java.direct.account.PlayersRequest.Companion.createRequest
import de.floribe2000.warships_java.direct.account.PlayersAchievmentsRequest
import de.floribe2000.warships_java.direct.account.PlayersPersonalDataFullRequest
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import de.floribe2000.warships_java.direct.warships.StatisticsRequest
import org.junit.Test
import java.lang.InterruptedException
import java.util.Properties
import java.io.FileInputStream

class PlayersTest {
    private val apiKey: String
    private val instanceName = "TEST"

    @Test
    fun testPlayersRequest() {
        createInstance(apiKey, instanceName)
        val request = createRequest().region(Region.EU).searchText("floribe")
        val result = request.fetch()
        assert(result.status.get()) { result }
    }

    @Test
    fun testPlayerPersonalDataRequest() {
        //TODO
        createInstance(apiKey, instanceName)
        val result = PlayersPersonalDataFullRequest.createRequest().region(Region.EU).addAccountId(537376379)
                .addExtraField(PlayersPersonalDataFullRequest.ExtraField.PVE)
                .addExtraField(PlayersPersonalDataFullRequest.ExtraField.RANK_SOLO).fetch()
        assert(result.status.get()) { result }
    }

    @Test
    fun testPlayersAchievements() {
        createInstance(apiKey, instanceName)
        val result = PlayersAchievmentsRequest.createRequest().region(Region.EU).addAccountId(537376379).fetch()
        assert(result.status.get()) { result }
        assert(PlayersAchievmentsRequest.AchievmentElement.retrieveElement("Solo Warrior") != null)
    }

    @Test
    fun testPlayersStatisticsByDate() {
        createInstance(apiKey, instanceName)
        val result1 = PlayerStatisticsByDateRequest.createRequest().region(Region.EU).accountId(537376379).addDate("20200318").fetch()
        assert(result1.status.get()) { result1 }
        val result = PlayerStatisticsByDateRequest.createRequest().region(Region.EU).accountId(537376379).addDate("20200228").addDate("20200118")
                .extra(PlayerStatisticsByDateRequest.ExtraField.PVE).fetch()
        assert(result.status.get()) { result }
    }

    @Test
    fun testPlayersWarshipsStatistics() {
        createInstance(apiKey, instanceName)
        val result = StatisticsRequest.createRequest().region(Region.EU).accountId(537376379).addExtraField(StatisticsRequest.ExtraField.PVE).fetch()
        assert(result.status.get()) { result }
    }

    @Test
    fun testAsyncPlayersRequest() {
        createInstance(apiKey, instanceName)
        val name = "floribe2000"
        createRequest().region(Region.EU).searchText(name).fetchAsync { result: Players ->
            assert(result.status.get()) { result }
            assert(result.data!![0].nickname == name) { result }
        }
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    @Test
    fun testAsyncPlayersRequestModified() {
        createInstance(apiKey, instanceName)
        val name = "floribe2000"
        val name2 = "MrDios"
        val request = createRequest().region(Region.EU).searchText(name)
        request.fetchAsync { result: Players ->
            try {
                Thread.sleep(500)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            println(result)
            assert(result.status.get()) { result }
            assert(result.data!![0].nickname == name) { result }
        }
        val players = request.searchText(name2).fetch()
        println(players)
        assert(players.status.get()) { players }
        assert(players.data!![0].nickname!!.contains(name2)) { players }
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    init {
        val properties = Properties()
        properties.load(FileInputStream("Warships.properties"))
        apiKey = properties.getProperty("APIKEY")
    }
}