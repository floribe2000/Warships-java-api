package de.floribe2000.warships_java.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.floribe2000.warships_java.requests.SimpleRateLimiter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

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

    T fetch();

    /**
     * Creates a url connection to the provided api and returns a object containing the received data.
     * <p>This method uses rate limiting but does not override the rate limit settings defined by {@link ApiBuilder#createInstance(String, boolean, boolean)}!</p>
     *
     * @param url    the url for the request
     * @param tClass the class of the api return object
     * @return an object of the given type containing the received data.
     */
    default T connect(String url, Class<T> tClass) {
        SimpleRateLimiter.waitForPermit();
        T result;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            result = GSON.fromJson(reader, tClass);
        } catch (Exception e) {
            //TODO improve error handling
            e.printStackTrace();
            try {
                return tClass.getDeclaredConstructor().newInstance();
            } catch (Exception e1) {
                return null;
            }
        }
        return result;
    }
}
