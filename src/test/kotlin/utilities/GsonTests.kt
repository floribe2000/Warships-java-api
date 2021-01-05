package utilities

import de.floribe2000.warships_java.direct.account.PlayersRequest
import de.floribe2000.warships_java.direct.api.fetchWithGson
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import de.floribe2000.warships_java.direct.general.ServerStatusRequest
import org.junit.jupiter.api.Test
import java.io.FileInputStream
import java.util.*

class GsonTests : ITestClass {
    override val apiKey: String
    override val instanceName: String = "TEST"

    @Test
    fun testGsonFetch() {
        setupApi()
        val request = ServerStatusRequest.createRequest().region(Region.EU).fetchWithGson()
        assert(request.status.get())
    }

    @Test
    fun testGsonPlayersRequest() {
        setupApi()
        val request = PlayersRequest.createRequest().region(Region.EU).searchText("floribe")
        val result = request.fetchWithGson()
        assert(result.status.get()) { result }
    }

    init {
        val properties = Properties()
        properties.load(FileInputStream("Warships.properties"))
        apiKey = properties.getProperty("APIKEY")
    }
}