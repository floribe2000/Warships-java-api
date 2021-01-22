@file:Suppress("UNUSED")

package de.floribe2000.warships_java.direct.api

import de.floribe2000.warships_java.direct.api.exceptions.ApiException
import de.floribe2000.warships_java.requests.SimpleRateLimiter
import java.net.UnknownHostException
import java.util.concurrent.CompletableFuture

/**
 * Allows to fetch a request synchronously with Gson.
 *
 * When using this method, null-safety is not guaranteed! This method should only be used from Java code, not from Kotlin.
 *
 * @param R The type of the request to use.
 * @param T The type of the expected api response.
 * @param url The url to connect to.
 *
 * @return An instance of the expected api response.
 */
inline fun <R : Any, reified T : IApiResponse> AbstractRequest<R, T>.fetchWithGson(url: String = buildUrl()): T {
    return connectWithGson(url, limiter)
}

/**
 * Allows to fetch a request asynchronously with Gson.
 *
 * When using this method, null-safety is not guaranteed! This method should only be used from Java code, not from Kotlin.
 *
 * @param R The type of the request to use.
 * @param T The type of the expected api response.
 * @param result Used to process the result of the operation.
 */
inline fun <R : Any, reified T : IApiResponse> AbstractRequest<R, T>.fetchAsyncWithGson(crossinline result: (T) -> Unit) {
    val url = buildUrl()
    CompletableFuture.runAsync { result(fetchWithGson(url)) }
}

/**
 * Creates a url connection to the provided api and returns a object containing the received data.
 *
 * This method uses rate limiting but does not override the rate limit settings defined by [ApiBuilder.createInstance]!
 *
 * @param url    the url for the request
 * @param limiter the [SimpleRateLimiter] instance used to manage api access
 * @return an object of the given type containing the received data.
 */
inline fun <reified T : IApiResponse> IRequestAction<T>.connectWithGson(url: String, limiter: SimpleRateLimiter): T {
    var result: T? = null
    var attempts = 0
    var lastException: Exception? = null

    //Retry failed request up to 5 times if request failed because of network issues
    while (result == null && attempts < 5) {
        //SimpleRateLimiter.waitForPermit(limiter);
        try {
            limiter.connectToApi(url).bufferedReader().use { reader ->
                result = IRequestAction.GSON.fromJson(reader, T::class.java)
                IRequestAction.validateResponse(result)
            }
        } catch (ue: UnknownHostException) {
            IRequestAction.LOG.error("An error occurred", ue)
            result = null
            lastException = ue
        } catch (ie: IllegalStateException) {
            IRequestAction.LOG.warn(ie.message)
            lastException = ie
            attempts += 5
        } catch (ea: ApiException) {
            IRequestAction.LOG.warn("Encountered an api exception", ea)
            lastException = ea
            result = null
        } catch (e: Exception) {
            lastException = e
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