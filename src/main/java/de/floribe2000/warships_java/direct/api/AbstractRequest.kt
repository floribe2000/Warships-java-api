package de.floribe2000.warships_java.direct.api;

import de.floribe2000.warships_java.direct.api.typeDefinitions.Region;
import de.floribe2000.warships_java.requests.SimpleRateLimiter;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * An abstract implementation of {@link IRequest} and {@link IRequestAction} that provides some utility methods to make it easier to use those interfaces.
 *
 * @param <R> The request class that implements this request
 * @param <T> The response class that implements the {@link IApiResponse} interface. This is also the return type of the {@link #fetch()} method.
 * @author floribe2000
 */
public abstract class AbstractRequest<R, T extends IApiResponse> implements IRequest<R>, IRequestAction<T> {

    private ApiBuilder instance = null;

    /**
     * Defines the api builder instance for this request.
     *
     * @param instanceName the name of the api builder instance
     */
    protected final void setInstance(String instanceName) {
        instance = ApiBuilder.getInstanceWithName(instanceName);
    }

    /**
     * A method to get the {@link SimpleRateLimiter} instance linked with the api builder of this request.
     *
     * @return the RateLimiter instance for this request
     */
    protected final SimpleRateLimiter getLimiter() {
        if (instance == null) {
            return ApiBuilder.getInstanceWithName(null).getRateLimiter();
        } else {
            return instance.getRateLimiter();
        }
    }

    /**
     * A method to get the identifier of the linked api builder instance.
     *
     * @return the identifier of the linked api builder instance
     */
    protected final String getInstanceName() {
        if (instance != null) {
            return instance.getInstanceName();
        } else {
            return null;
        }
    }

    /**
     * A utility method to make it easier to check the region.
     *
     * @param region the region to check
     * @throws IllegalArgumentException Thrown if the region is null.
     */
    protected void checkRegion(Region region) {
        if (region == null) {
            throw new IllegalArgumentException("Region must not be null.");
        }
    }

    /**
     * A method to send a request and return the api response.
     *
     * @param url the url for the request
     * @return an instance of {@link T} that represents the api response
     */
    abstract protected T fetch(String url);

    /**
     * Executes an asynchronous request.
     *
     * @param result a consumer for the result of the request
     */
    public final void fetchAsync(Consumer<T> result) {
        String url = buildUrl();
        CompletableFuture.runAsync(() -> result.accept(fetch(url)));
    }

    /**
     * Executes the request and returns an instance of the api response.
     *
     * @return an instance of the api response
     */
    @Override
    public final T fetch() {
        return fetch(buildUrl());
    }
}
