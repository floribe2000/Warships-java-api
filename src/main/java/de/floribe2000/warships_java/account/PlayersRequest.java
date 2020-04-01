package de.floribe2000.warships_java.account;

import de.floribe2000.warships_java.api.*;
import de.floribe2000.warships_java.requests.SimpleRateLimiter;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A class to create and execute requests to retrieve a list of players based on a search string.
 * <p>Up to 100 players can be retrieved, if there is an exact match only one player will be returned.</p>
 *
 * <p>For details about this request see <a href="https://developers.wargaming.net/reference/all/wows/account/list/">WG api reference</a>.</p>
 *
 * @author floribe2000
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayersRequest extends AbstractRequest<PlayersRequest> implements IRequestAction<Players> {

    /**
     * A Logger instance used to log events of this class
     */
    private final Logger LOG = LoggerFactory.getLogger(getClass().getSimpleName());

    /**
     * The server region for this request
     */
    private Region region = null;

    /**
     * The language for the api response
     */
    private Language language = null;

    /**
     * The search text for this request
     */
    private String searchText = null;

    /**
     * The response fields for this request
     */
    private Set<ResponseField> fields = new HashSet<>();

    /**
     * Creates a new, empty request
     *
     * @return an instance of this class
     */
    public static PlayersRequest createRequest() {
        return new PlayersRequest();
    }

    @Override
    public PlayersRequest region(Region region) {
        this.region = region;
        return this;
    }

    @Override
    public PlayersRequest language(Language language) {
        this.language = language;
        return this;
    }

    @Override
    public PlayersRequest apiBuilder(String instanceName) {
        setInstance(instanceName);
        return this;
    }

    /**
     * Sets the search text for the request.
     * <p>Can be called multiple times. Important: The existing search text is replaced by this method!</p>
     *
     * @param text the new search text
     * @return the instance of this request
     */
    public PlayersRequest searchText(String text) {
        searchText = text;
        return this;
    }

    /**
     * Adds a field to the request while keeping all existing fields
     *
     * @param fields the fields to add
     * @return this instance
     */
    public PlayersRequest addFields(ResponseField... fields) {
        if (this.fields == null) {
            this.fields = new HashSet<>();
        }
        this.fields.addAll(Arrays.asList(fields));
        return this;
    }

    /**
     * Replaces all currently set fields with a new list of fields
     *
     * @param fields the new fields
     * @return this instance
     */
    public PlayersRequest fields(ResponseField... fields) {
        this.fields = new HashSet<>();
        this.fields.addAll(Arrays.asList(fields));
        return this;
    }


    /**
     * Executes a request and returns the result of the request.
     * <p>All requests are executed synchronous on this thread. It is safe to execute this in a new thread if it is required to be run asynchronous.</p>
     *
     * @return an instance of {@link Players} that contains all requested player data. If the request fails, an empty instance is returned.
     * @throws IllegalArgumentException <ul>
     *                                  <li>If this method is called and region is null.</li>
     *                                  <li>If this method is called and searchText is null.</li>
     *                                  </ul>
     */
    @Override
    public Players fetch() {
        if (region == null || searchText == null) {
            throw new IllegalArgumentException("You can't use this method before setting all parameters");
        }
        String path = "/wows/account/list/";
        String url = baseUrl(region, path, language) + "&search=" + searchText + buildFieldString(FieldType.FIELDS, fields);
//        Players result;
//        SimpleRateLimiter.waitForPermit();
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
//            result = GSON.fromJson(reader, Players.class);
//        } catch (Exception e) {
//            LOG.error("An exception occured", e);
//            result = new Players();
//        }
        return connect(url, Players.class, getLimiter());
    }

    /**
     * All response fields of the api response.
     * <p>By default, both fields are shown. Only use them if you need only one of them.</p>
     */
    @AllArgsConstructor
    public enum ResponseField implements IResponseFields {
        ACCOUNT_ID("account_id"),
        NICKNAME("nickname");

        @NonNull
        private String key;

        @Override
        public String retrieveKey() {
            return key;
        }
    }

}
