package de.floribe2000.warships_java.direct.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import de.floribe2000.warships_java.direct.api.exceptions.ApiException
import de.floribe2000.warships_java.requests.SimpleRateLimiter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.UnknownHostException

/**
 * An interface to mark all RequestActions.
 *
 * All request action implementations have to provide an implementation of the fetch method to allow retrieval of data.
 * However, the required fields can be different for each implementation.
 *
 * @param <T> the return type for the fetch method, has to implement [IApiResponse]
 * @author floribe2000
</T> */
interface IRequestAction<T : IApiResponse> {
    /**
     * Executes the request.
     *
     * @return am instance of type [T]
     */
    fun fetch(): T
    fun buildUrl(): String

    /**
     * Executes an asynchronous request.
     *
     * Avoid modifying the request before this request was completed!
     *
     * If you want to send multiple asynchronous requests with different data, create a copy of this request before executing it.
     *
     * @param result a consumer for the result of the request
     */
    fun fetchAsync(result: (T) -> Unit)

    /**
     * Creates a url connection to the provided api and returns a object containing the received data.
     *
     * This method uses rate limiting but does not override the rate limit settings defined by [ApiBuilder.createInstance]!
     *
     * @param url    the url for the request
     * @param tClass the class of the api return object
     * @param limiter the [SimpleRateLimiter] instance used to manage api access
     * @return an object of the given type containing the received data.
     */
    fun connect(url: String, tClass: Class<T>, limiter: SimpleRateLimiter): T {
        var result: T? = null
        var attempts = 0
        var lastException: Exception? = null

        //Retry failed request up to 5 times if request failed because of network issues
        while (result == null && attempts < 5) {
            //SimpleRateLimiter.waitForPermit(limiter);
            try {
                limiter.connectToApi(url).bufferedReader().use { reader ->
                    result = GSON.fromJson(reader, tClass)
                    val response: IApiResponse? = result
                    if (response?.error?.code == 407) {
                        throw IllegalStateException(response.error?.message ?: "Cannot read error message")
                    } else if (response?.error?.code == 504) {
                        throw ApiException(response.error?.message.toString(), response.error?.code ?: 504)
                    }
                }
            } catch (ue: UnknownHostException) {
                LOG.error("An error occurred", ue)
                ue.printStackTrace()
                result = null
                lastException = ue
            } catch (ie: IllegalStateException) {
                LOG.warn(ie.message)
                ie.printStackTrace()
                lastException = ie
                attempts += 5
            } catch (ea: ApiException) {
                LOG.warn("Encountered an api exception", ea)
                lastException = ea
                result = null
            } catch (e: Exception) {
                lastException = e
                e.printStackTrace()
                LOG.error("An error occurred", e)
                break
            }
            attempts++
        }

        return result ?: try {
            tClass.getDeclaredConstructor().newInstance()
        } catch (e1: Exception) {
            throw IllegalStateException(
                "Unable to initialize a default instance. Last exception while trying to connect to the api:",
                lastException
            )
        }
    }

    /**
     * A companion object that provides some constant variables that are usable outside of this class as well.
     */
    companion object {
        /**
         * The gson instance used for deserialization by all classes that implement this interface.
         *
         * Always use this instance, do not create a gson instance on your own if you don't know exactly what you are doing!
         */
        val GSON: Gson = GsonBuilder().serializeNulls().setPrettyPrinting().create()

        /**
         * A logger instance for the methods of this interface.
         */
        val LOG: Logger = LoggerFactory.getLogger("RequestAction")
    }
}

inline fun <reified T : IApiResponse> IRequestAction<T>.connectKotlinx(url: String, limiter: SimpleRateLimiter): T {
    var result: T? = null
    var attempts = 0
    var lastException: Exception? = null

    //Retry failed request up to 5 times if request failed because of network issues
    while (result == null && attempts < 5) {
        //SimpleRateLimiter.waitForPermit(limiter);
        try {
            limiter.connectToApi(url).bufferedReader().use { reader ->
                result = Json.decodeFromString(reader.readText())
                val response: IApiResponse? = result
                if (response?.error?.code == 407) {
                    throw IllegalStateException(response.error?.message ?: "Cannot read error message")
                } else if (response?.error?.code == 504) {
                    throw ApiException(response.error?.message.toString(), response.error?.code ?: 504)
                }
            }
        } catch (ue: UnknownHostException) {
            IRequestAction.LOG.error("An error occurred", ue)
            ue.printStackTrace()
            result = null
            lastException = ue
        } catch (ie: IllegalStateException) {
            IRequestAction.LOG.warn(ie.message)
            ie.printStackTrace()
            lastException = ie
            attempts += 5
        } catch (ea: ApiException) {
            IRequestAction.LOG.warn("Encountered an api exception", ea)
            lastException = ea
            result = null
        } catch (e: Exception) {
            lastException = e
            e.printStackTrace()
            IRequestAction.LOG.error("An error occurred", e)
            break
        }
        attempts++
    }

    return result ?: try {
        T::class.java.getDeclaredConstructor().newInstance()
    } catch (e1: Exception) {
        throw IllegalStateException(
            "Unable to initialize a default instance. Last exception while trying to connect to the api:",
            lastException
        )
    }
}