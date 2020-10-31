package de.floribe2000.warships_java.direct.api

import de.floribe2000.warships_java.requests.SimpleRateLimiter
import de.floribe2000.warships_java.utilities.AbstractRequestService
import org.slf4j.LoggerFactory
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap


/**
 * Provides a basic connector to the api.
 *
 * This class is used to provide the api key for all requests.
 *
 * @author floribe2000
 */
class ApiBuilder(
        /**
         * The rate limiter instance linked to this api builder instance
         */
        val rateLimiter: SimpleRateLimiter,
        /**
         * The unique identifier of this instance
         */
        val instanceName: String,
        /**
         * The api key that is used to connect to the wargaming api
         */
        private val apiKey: String) {

    /**
     * A method to get the api key of this instance as url parameter that can be used as first parameter of a request url
     *
     * @return the api key as first url parameter
     */
    fun getApiKeyAsParam(): String {
        return "?application_id=$apiKey"
    }

    /**
     * Closes the instance
     */
    private fun close() {
        rateLimiter.close()
    }

    companion object {
        /**
         * A logger to log events about this class
         */
        private val LOG = LoggerFactory.getLogger("ApiBuilder")

        /**
         * The last automatically assigned id for a new instance without specified identifier
         */
        private var lastNum = 0

        /**
         * The default api type for new api instances
         */
        private var defaultType = SimpleRateLimiter.ApiType.CLIENT

        /**
         * The instances of the ApiBuilder
         */
        private val instances = Collections.synchronizedMap(HashMap<String, ApiBuilder>())

        /**
         * The unique identifier of the primary api instance
         */
        private var primaryInstance: String? = null

        /**
         * Creates an ApiBuilder instance to be used for api requests.
         *
         * @param apiKey           the api key to use when connecting to the wargaming api
         * @throws IllegalStateException if there is already an instance defined and ignoreError is set to false
         */
        fun createInstance(apiKey: String, apiId: String) {
            createInstance(apiKey, true, apiId)
        }

        /**
         * Creates an ApiBuilder instance to be used for api requests.
         *
         * @param apiKey           the api key to use when connecting to the wargaming api
         * @param rateLimitEnabled a boolean to determine if rate limiting should be enabled. It is recommended to set this to true!
         * @throws IllegalStateException if there is already an instance defined and ignoreError is set to false
         */
        @JvmOverloads
        fun createInstance(apiKey: String, rateLimitEnabled: Boolean = true) {
            val apiId = "API_" + lastNum++
            createInstance(apiKey, rateLimitEnabled, apiId)
        }

        /**
         * Creates an ApiBuilder instance to be used for api requests.
         *
         * @param apiKey the api key to use when connecting to the wargaming api
         * @param type   the [ApiType][de.floribe2000.warships_java.requests.SimpleRateLimiter.ApiType] of the new instance (client or server)
         * @throws IllegalStateException if there is already an instance defined and ignoreError is set to false
         */
        fun createInstance(apiKey: String, type: SimpleRateLimiter.ApiType) {
            val apiId = "API_" + lastNum++
            createInstance(apiKey, type, apiId)
        }

        /**
         * Creates an ApiBuilder instance to be used for api requests.
         *
         * @param apiKey       the api key to use when connecting to the wargaming api
         * @param type         the [ApiType][de.floribe2000.warships_java.requests.SimpleRateLimiter.ApiType] of the new instance (client or server)
         * @param instanceName the name of the instance to create
         * @throws IllegalStateException if there is already an instance defined and ignoreError is set to false
         */
        fun createInstance(apiKey: String, type: SimpleRateLimiter.ApiType, instanceName: String) {
            createInstance(apiKey, true, instanceName, type)
        }

        /**
         * Creates an ApiBuilder instance to be used for api requests.
         *
         * @param apiKey           the api key to use when connecting to the wargaming api
         * @param rateLimitEnabled a boolean to determine if rate limiting should be enabled. It is recommended to set this to true!
         * @param instanceName     the name of the instance to create
         * @param type             the [ApiType][de.floribe2000.warships_java.requests.SimpleRateLimiter.ApiType] of the new instance (client or server)
         * @throws IllegalStateException if there is already an instance defined and ignoreError is set to false
         */
        @JvmOverloads
        fun createInstance(apiKey: String, rateLimitEnabled: Boolean, instanceName: String, type: SimpleRateLimiter.ApiType = defaultType) {
            if (instances[instanceName] != null) {
                return
            }
            val instance = ApiBuilder(SimpleRateLimiter(rateLimitEnabled, type), instanceName, apiKey)
            instances[instanceName] = instance
            if (primaryInstance == null) {
                primaryInstance = instanceName
            }
        }

        /**
         * Creates a new ApiBuilder instance if there is no active existing instance.
         *
         * @param apiKey the api key to use when connecting to the wargaming api
         */
        fun createInstanceIfNoneExists(apiKey: String) {
            if (instances.isEmpty()) {
                createInstance(apiKey)
            }
        }

        /**
         * Creates a new ApiBuilder instance if there is no active existing instance.
         *
         * @param apiKey the api key to use when connecting to the wargaming api
         * @param type   the [ApiType][de.floribe2000.warships_java.requests.SimpleRateLimiter.ApiType] of the new instance (client or server)
         */
        fun createInstanceIfNoneExists(apiKey: String, type: SimpleRateLimiter.ApiType) {
            if (instances.isEmpty()) {
                createInstance(apiKey, type)
            }
        }

        /**
         * Returns the api key of an instance as url param.
         *
         * @param instanceName the instance for the request
         * @return the api key of the instance
         * @throws NullPointerException If there is no such instance, an exception is thrown.
         * @see ApiBuilder.getApiKeyAsParam
         */
        @JvmStatic
        fun getApiKeyAsParam(instanceName: String?): String? {
            var internalInstanceName = instanceName
            if (instances.isEmpty()) {
                return null
            }
            if (internalInstanceName == null) {
                internalInstanceName = primaryInstance
            }
            return instances[internalInstanceName]?.getApiKeyAsParam()
        }

        /**
         * Returns an instance with the specified identifier
         *
         * @param instanceName the identifier of the requested instance
         * @return the instance or null if there is no such instance
         */
        fun getInstanceWithName(instanceName: String?): ApiBuilder {
            var internalInstanceName = instanceName
            if (internalInstanceName == null) {
                internalInstanceName = primaryInstance
            }
            return instances[internalInstanceName]
                    ?: throw IllegalArgumentException("There is no instance for this key")
        }

        /**
         * A method to get the current number of active instances
         *
         * @return the number of active instances as integer
         */
        @JvmStatic
        fun getInstanceSize(): Int {
            return instances.size
        }

        /**
         * Closes the instance with the specified identifier
         *
         * @param instanceName the identifier of the instance that should be closed
         * @throws IOException If an error occurs while closing the instance
         */
        fun closeInstance(instanceName: String) {
            instances[instanceName]?.close()
            instances.remove(instanceName)
        }

        /**
         * Initiates a shutdown of all instances. Tries to close all instances that are currently registered.
         */
        @JvmStatic
        fun shutdown() {
            instances.forEach { (_: String?, value: ApiBuilder?) ->
                try {
                    value?.close()
                } catch (e: IOException) {
                    LOG.error("Error while shutting down instance " + value?.instanceName, e)
                }
            }
            instances.clear()
            primaryInstance = null
            lastNum = 0
            AbstractRequestService.reset()
        }

        /**
         * A method to show if there is at least 1 active instance of this class.
         *
         * @return true if there is at least an active instance, false if not
         */
        fun isInitialized(): Boolean {
            return instances.isNotEmpty()
        }
    }
}