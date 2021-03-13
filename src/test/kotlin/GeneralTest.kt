import de.floribe2000.warships_java.direct.account.PlayersPersonalDataFullRequest
import de.floribe2000.warships_java.direct.api.ApiBuilder.Companion.getApiKeyAsParam
import de.floribe2000.warships_java.direct.api.ApiBuilder.Companion.getInstanceSize
import de.floribe2000.warships_java.direct.api.ApiBuilder.Companion.shutdown
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import de.floribe2000.warships_java.direct.general.ServerStatusRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertTimeout
import utilities.ITestClass
import java.io.FileInputStream
import java.time.Duration
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class GeneralTest : ITestClass {
    override val instanceName = "TEST"

    @Test
    fun testRateLimiter() {
        val accountId = 537376379
        setupApi()
        val instanceSize = getInstanceSize()
        val request = PlayersPersonalDataFullRequest().region(Region.EU).addAccountId(accountId.toLong())
        val service = Executors.newCachedThreadPool()
        val requests = 100
        val errorCount = AtomicInteger(0)

        assertTimeout(Duration.ofSeconds((requests.toDouble() / 10 * 1.5).toLong() + 1)) {
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
                service.awaitTermination(100, TimeUnit.SECONDS)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        assert(errorCount.get() == 0) { "There were errors during the test." }
        assert(getInstanceSize() == instanceSize) { getInstanceSize().toString() + ", expected size of " + instanceSize }
    }

    @Test
    fun testShutdown() {
        setupApi()
        assert(getApiKeyAsParam(null) != null) { "Api key is null" }
        shutdown()
        assert(getInstanceSize() == 0) { "Instance list not cleared after shutdown" }
        setupApi(instanceName = "NEWAPP")
        assert(getApiKeyAsParam(null) != null) { "Api key is null" }
        shutdown()
    }

    @Test
    fun testServerStatus() {
        setupApi()
        val request = ServerStatusRequest.createRequest().region(Region.EU)
        val response = request.fetch()
        assert(response.status.get()) {
            "Invalid response state:\n ${response.error.toString()}"
        }
    }
}