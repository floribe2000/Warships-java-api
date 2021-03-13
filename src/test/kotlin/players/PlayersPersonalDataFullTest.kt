package players

import de.floribe2000.warships_java.direct.account.PlayersPersonalDataFullRequest
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import org.junit.jupiter.api.Test
import utilities.ITestClass

class PlayersPersonalDataFullTest : ITestClass {
    override val instanceName: String = "TEST"

    @Test
    fun fetchFullList() {
        setupApi()

        val playerList: List<Long> = DataGenerator.retrieveFullPlayerList()
        val request = PlayersPersonalDataFullRequest().region(Region.EU).accountIds(playerList)

        val response = request.fetch()

        assert(response.status.get()) { response }
    }

    @Test
    fun playersFetchFullListWithHiddenAccounts() {
        setupApi()

        val playerList: List<Long> = DataGenerator.retrievePlayerListWithHiddenAccounts()
        val request = PlayersPersonalDataFullRequest().region(Region.EU).accountIds(playerList)

        val response = try {
            request.fetch()
        } catch (e: Exception) {
            println(e.stackTrace)
            throw AssertionError(e.message)
        }

        assert(response.status.get()) { response }
    }

    @Test
    fun testPlayerPersonalDataRequest() {
        setupApi()

        val result = PlayersPersonalDataFullRequest().region(Region.EU).addAccountId(537376379)
            .addExtraField(PlayersPersonalDataFullRequest.ExtraField.PVE)
            .addExtraField(PlayersPersonalDataFullRequest.ExtraField.RANK_SOLO).fetch()

        assert(result.status.get()) { result }
    }

    @Test
    fun testExtraFields() {
        setupApi()
        val result = PlayersPersonalDataFullRequest().region(Region.EU).addAccountId(537376379).extraFields(
            PlayersPersonalDataFullRequest.ExtraField.PVE, PlayersPersonalDataFullRequest.ExtraField.PVP_DIV2).fetch()

        assert(result.status.get()) { result }
        assert(result.data.values.first()?.statistics?.pve != null)
        assert(result.data.values.first()?.statistics?.pvp_div2 != null)
    }
}