package utilities

import de.floribe2000.warships_java.direct.api.ApiBuilder
import de.floribe2000.warships_java.direct.api.IApiResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * An interface used to define constraints for all test classes.
 * Also provides utilities to setup the test api builder instance.
 *
 * @author floribe2000
 */
interface ITestClass {

    val instanceName: String

    fun setupApi(apiKey: String = System.getenv("APIKEY"), instanceName: String = this.instanceName) {
        ApiBuilder.createInstance(apiKey, instanceName = instanceName, ignoreUnknownKeys = allowUnknownKeys)
    }

    companion object {
        const val allowUnknownKeys: Boolean = true

        inline fun <reified T : IApiResponse> printApiResponse(apiResponse: T) {
            val json = Json {
                prettyPrint = true
                isLenient = true
            }

            println(json.encodeToString(apiResponse))
        }
    }
}