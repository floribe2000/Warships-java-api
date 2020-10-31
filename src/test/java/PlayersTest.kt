import de.floribe2000.warships_java.direct.account.*
import de.floribe2000.warships_java.direct.api.ApiBuilder
import de.floribe2000.warships_java.direct.account.PlayersRequest
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import de.floribe2000.warships_java.direct.warships.StatisticsRequest
import org.junit.jupiter.api.Test
import java.lang.InterruptedException
import java.util.Properties
import java.io.FileInputStream

class PlayersTest {
    private val apiKey: String
    private val instanceName = "TEST"

    @Test
    fun testPlayersRequest() {
        ApiBuilder.createInstance(apiKey, instanceName)
        val request = PlayersRequest.createRequest().region(Region.EU).searchText("floribe")
        val result = request.fetch()
        assert(result.status.get()) { result }
    }

    @Test
    fun testPlayerPersonalDataRequest() {
        //TODO
        ApiBuilder.createInstance(apiKey, instanceName)
        val result = PlayersPersonalDataFullRequest.createRequest().region(Region.EU).addAccountId(537376379)
                .addExtraField(PlayersPersonalDataFullRequest.ExtraField.PVE)
                .addExtraField(PlayersPersonalDataFullRequest.ExtraField.RANK_SOLO).fetch()
        assert(result.status.get()) { result }
    }

    @Test
    fun testPlayersAchievements() {
        ApiBuilder.createInstance(apiKey, instanceName)
        val result = PlayersAchievmentsRequest.createRequest().region(Region.EU).addAccountId(537376379).fetch()
        assert(result.status.get()) { result }
    }

    @Test
    fun testPlayersStatisticsByDate() {
        ApiBuilder.createInstance(apiKey, instanceName)
        val result1 = PlayerStatisticsByDateRequest.createRequest().region(Region.EU).accountId(537376379).addDate("20200318").fetch()
        assert(result1.status.get()) { result1 }
        val result = PlayerStatisticsByDateRequest.createRequest().region(Region.EU).accountId(537376379).addDate("20200228").addDate("20200118")
                .extra(PlayerStatisticsByDateRequest.ExtraField.PVE).fetch()
        assert(result.status.get()) { result }
    }

    @Test
    fun testPlayersWarshipsStatistics() {
        ApiBuilder.createInstance(apiKey, instanceName)
        val result = StatisticsRequest.createRequest().region(Region.EU).accountId(537376379).addExtraField(StatisticsRequest.ExtraField.PVE).fetch()
        assert(result.status.get()) { result }
    }

    @Test
    fun testAsyncPlayersRequest() {
        ApiBuilder.createInstance(apiKey, instanceName)
        val name = "floribe2000"
        PlayersRequest.createRequest().region(Region.EU).searchText(name).fetchAsync { result: Players ->
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
        ApiBuilder.createInstance(apiKey, instanceName)
        val name = "floribe2000"
        val name2 = "MrDios"
        val request = PlayersRequest.createRequest().region(Region.EU).searchText(name)
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