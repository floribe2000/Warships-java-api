import de.floribe2000.warships_java.direct.account.*
import de.floribe2000.warships_java.direct.account.PlayersRequest
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import de.floribe2000.warships_java.direct.warships.StatisticsRequest
import org.junit.jupiter.api.Test
import utilities.ITestClass
import java.lang.InterruptedException

class PlayersTest : ITestClass {
    override val instanceName = "TEST"
    private val invalidInstanceName = "TEST-INVALID"

    @Test
    fun testPlayersRequest() {
        setupApi()
        val request = PlayersRequest.createRequest().region(Region.EU).searchText("floribe")
        val result = request.fetch()
        assert(result.status.get()) { result }
    }

    @Test
    fun testFailedPlayersRequest() {
        setupApi("apiKey", instanceName = invalidInstanceName)
        val request = PlayersRequest.createRequest().apiBuilder(invalidInstanceName).region(Region.EU).searchText("florible")
        val result = request.fetch()
        println(result)
        println(result.data)
        assert(!result.status.get()) { result }
    }

    @Test
    fun testPlayersAchievements() {
        setupApi()
        val result = PlayersAchievementsRequest().region(Region.EU).addAccountId(537376379).fetch()
        assert(result.status.get()) { result }
    }

    @Test
    fun testPlayersStatisticsByDate() {
        setupApi()
        val result1 = PlayerStatisticsByDateRequest.createRequest().region(Region.EU).accountId(537376379).addDate("20200318").fetch()
        assert(result1.status.get()) { result1 }
        val result = PlayerStatisticsByDateRequest.createRequest().region(Region.EU).accountId(537376379).addDate("20200228").addDate("20200118")
                .extra(PlayerStatisticsByDateRequest.ExtraField.PVE).fetch()
        assert(result.status.get()) { result }
    }

    @Test
    fun testPlayersWarshipsStatistics() {
        setupApi()
        val result = StatisticsRequest.createRequest().region(Region.EU).accountId(537376379).addExtraField(StatisticsRequest.ExtraField.PVE).fetch()
        assert(result.status.get()) { result }
    }

    @Test
    fun testAsyncPlayersRequest() {
        setupApi()
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
        setupApi()
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
}