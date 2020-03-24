package de.floribe2000.warships_java.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * An interface to mark all RequestActions.
 * <p>All request action implementations have to provide an implementation of the fetch method to allow retrieval of data.
 * However, the required fields can be different for each implementation.</p>
 *
 * @param <T> the return type for the fetch method, has to implement {@link IApiResponse}
 * @author floribe2000
 */
public interface IRequestAction<T extends IApiResponse> {

    Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    T fetch();
}
