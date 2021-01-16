package players

import de.floribe2000.warships_java.direct.account.PlayersPersonalDataFullRequest
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import org.junit.jupiter.api.Test
import utilities.ITestClass
import java.io.FileInputStream
import java.util.*

class PlayersPersonalDataFullTest : ITestClass {

    override val apiKey: String
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
    fun testPlayerPersonalDataRequest() {
        setupApi()

        val result = PlayersPersonalDataFullRequest.createRequest().region(Region.EU).addAccountId(537376379)
            .addExtraField(PlayersPersonalDataFullRequest.ExtraField.PVE)
            .addExtraField(PlayersPersonalDataFullRequest.ExtraField.RANK_SOLO).fetch()

        assert(result.status.get()) { result }
    }

    init {
        val properties = Properties()
        properties.load(FileInputStream("Warships.properties"))
        apiKey = properties.getProperty("APIKEY")
    }
}