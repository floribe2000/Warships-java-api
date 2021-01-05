@file:Suppress("UNUSED")

package de.floribe2000.warships_java.direct.api

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
    return connectWithGson(url, T::class.java, limiter)
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