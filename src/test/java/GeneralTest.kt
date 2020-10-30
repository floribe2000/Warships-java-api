import de.floribe2000.warships_java.direct.account.PlayersPersonalDataFullRequest
import de.floribe2000.warships_java.direct.api.ApiBuilder
import de.floribe2000.warships_java.direct.api.ApiBuilder.Companion.getApiKeyAsParam
import de.floribe2000.warships_java.direct.api.ApiBuilder.Companion.getInstanceSize
import de.floribe2000.warships_java.direct.api.ApiBuilder.Companion.shutdown
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import de.floribe2000.warships_java.direct.general.ServerStatusRequest
import org.junit.Test
import java.io.FileInputStream
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class GeneralTest {
    private val apiKey: String
    private val instanceName = "TEST"

    @Test
    fun testRateLimiter() {
        val accountId = 537376379
        ApiBuilder.createInstance(apiKey, instanceName)
        val instanceSize = getInstanceSize()
        val request = PlayersPersonalDataFullRequest.createRequest().region(Region.EU).addAccountId(accountId.toLong())
        val service = Executors.newCachedThreadPool()
        val start = System.currentTimeMillis()
        val requests = 100
        val errorCount = AtomicInteger(0)
        for (i in 0 until requests) {
            service.execute {
                val result = try {
                    request.fetch()
                } catch (e: IllegalStateException) {
                    errorCount.addAndGet(1)
                    throw AssertionError("Request was unable to be processed.")
                }
                assert(result.status.get()) { result.error?.message ?: "Unable to read error message." }
            }
        }
        service.shutdown()
        try {
            service.awaitTermination(20, TimeUnit.SECONDS)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val time = (System.currentTimeMillis() - start).toDouble() / 1000
        assert(time <= requests.toDouble() / 10 * 1.5 + 1) { time }
        assert(errorCount.get() == 0) { "There were errors during the test." }
        println("Time passed: " + time + "s")
        assert(getInstanceSize() == instanceSize) { getInstanceSize().toString() + ", expected size of " + instanceSize }
    }

    @Test
    fun testShutdown() {
        ApiBuilder.createInstance(apiKey)
        assert(getApiKeyAsParam(null) != null) { "Api key is null" }
        shutdown()
        assert(getInstanceSize() == 0) { "Instance list not cleared after shutdown" }
        ApiBuilder.createInstance(apiKey, "NEWAPP")
        assert(getApiKeyAsParam(null) != null) { "Api key is null" }
        shutdown()
    }

    @Test
    fun testServerStatus() {
        ApiBuilder.createInstanceIfNoneExists(apiKey)
        val request = ServerStatusRequest.createRequest().region(Region.EU)
        val response = request.fetch()
        assert(response.status.get()) {
            "Invalid response state:\n ${response.error.toString()}".trimIndent()
        }
    }

    init {
        val properties = Properties()
        properties.load(FileInputStream("Warships.properties"))
        apiKey = properties.getProperty("APIKEY")
    }
}