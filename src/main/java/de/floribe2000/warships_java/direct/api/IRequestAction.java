package de.floribe2000.warships_java.direct.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.floribe2000.warships_java.requests.SimpleRateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.function.Consumer;

/**
 * An interface to mark all RequestActions.
 * <p>All request action implementations have to provide an implementation of the fetch method to allow retrieval of data.
 * However, the required fields can be different for each implementation.</p>
 *
 * @param <T> the return type for the fetch method, has to implement {@link IApiResponse}
 * @author floribe2000
 */
public interface IRequestAction<T extends IApiResponse> {

    /**
     * The gson instance used for deserialization by all classes that implement this interface.
     * <p>Always use this instance, do not create a gson instance on your own if you don't know exactly what you are doing!</p>
     */
    Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    /**
     * A logger instance for the methods of this interface.
     */
    Logger LOG = LoggerFactory.getLogger("RequestAction");

    /**
     * Executes the request.
     *
     * @return am instance of type {@link T}
     */
    T fetch();

    String buildUrl();

    /**
     * Executes an asynchronous request.
     * <p>Avoid modifying the request before this request was completed!</p>
     * <p>If you want to send multiple asynchronous requests with different data, create a copy of this request before executing it.</p>
     *
     * @param result a consumer for the result of the request
     */
    void fetchAsync(Consumer<T> result);

    /**
     * Creates a url connection to the provided api and returns a object containing the received data.
     * <p>This method uses rate limiting but does not override the rate limit settings defined by {@link ApiBuilder#createInstance(String, boolean)}!</p>
     *
     * @param url    the url for the request
     * @param tClass the class of the api return object
     * @return an object of the given type containing the received data.
     */
    default T connect(String url, Class<T> tClass, SimpleRateLimiter limiter) {
        SimpleRateLimiter.waitForPermit(limiter);
        T result = null;
        int attempts = 0;

        //Retry failed request up to 5 times if request failed because of network issues
        while (result == null && attempts < 5) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
                result = GSON.fromJson(reader, tClass);
            } catch (UnknownHostException ue) {
                LOG.error("An error occurred", ue);
                result = null;
            } catch (Exception e) {
                LOG.error("An error occurred", e);
                try {
                    result = tClass.getDeclaredConstructor().newInstance();
                } catch (Exception e1) {
                    result = null;
                }
                break;
            }
            attempts++;
        }
        return result;
    }
}
