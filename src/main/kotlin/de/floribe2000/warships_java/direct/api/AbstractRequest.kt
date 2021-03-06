package de.floribe2000.warships_java.direct.api

import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import de.floribe2000.warships_java.requests.SimpleRateLimiter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.CompletableFuture

/**
 * An abstract implementation of [IRequest] and [IRequestAction] that provides some utility methods to make it easier to use those interfaces.
 *
 * @param <R> The request class that implements this request
 * @param <T> The response class that implements the [IApiResponse] interface. This is also the return type of the [.fetch] method.
 * @author floribe2000
</T></R> */
abstract class AbstractRequest<R : Any, T : IApiResponse> : IRequest<R>, IRequestAction<T> {

    private var instance: ApiBuilder? = null

    protected open val log: Logger = LoggerFactory.getLogger(this::class.simpleName)

    /**
     * Defines the api builder instance for this request.
     *
     * @param instanceName the name of the api builder instance
     */
    protected fun setInstance(instanceName: String?) {
        instance = ApiBuilder.getInstanceWithName(instanceName)
    }

    /**
     * A method to get the [SimpleRateLimiter] instance linked with the api builder of this request.
     *
     * @return the RateLimiter instance for this request
     */
    val limiter: SimpleRateLimiter
        get() = instance?.rateLimiter ?: ApiBuilder.getInstanceWithName(null).rateLimiter

    /**
     * A method to get the identifier of the linked api builder instance.
     *
     * @return the identifier of the linked api builder instance
     */
    protected val instanceName: String?
        get() = instance?.instanceName

    /**
     * A utility method to make it easier to check the region.
     *
     * @param region the region to check
     * @throws IllegalArgumentException Thrown if the region is null.
     */
    protected fun checkRegion(region: Region?) {
        requireNotNull(region) { "Region must not be null." }
    }

    /**
     * A method to send a request and return the api response.
     *
     * @param url the url for the request
     * @return an instance of [T] that represents the api response
     */
    protected abstract fun fetch(url: String): T

    /**
     * Executes an asynchronous request.
     *
     * @param result a consumer for the result of the request
     */
    override fun fetchAsync(result: (T) -> Unit) {
        val url = buildUrl()
        CompletableFuture.runAsync { result(fetch(url)) }
    }

    /**
     * Executes the request and returns an instance of the api response.
     *
     * @return an instance of the api response
     */
    override fun fetch(): T {
        return fetch(buildUrl())
    }
}