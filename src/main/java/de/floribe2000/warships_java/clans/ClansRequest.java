package de.floribe2000.warships_java.clans;

import de.floribe2000.warships_java.api.FieldType;
import de.floribe2000.warships_java.api.IRequestAction;
import de.floribe2000.warships_java.api.Language;
import de.floribe2000.warships_java.api.Region;
import de.floribe2000.warships_java.requests.SimpleRateLimiter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * A class to create and execute requests to retrieve a list of clans based on their name or tag.
 * <p>Up to 100 clans can be retrieved in one request, if there are more matches you have to execute the request multiple times while increasing the page number.
 * Details about the total amount of hits can be retrieved by analyzing the {@link de.floribe2000.warships_java.common.Meta Meta} object of the api response
 * returned by {@link #fetch()}.</p>
 *
 * @author floribe2000
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClansRequest implements IRequestAction<Clans>, IClanRequest<ClansRequest> {

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
     * The page for this request. Minimum value 1, maximum value undefined.
     */
    private int page = 1;

    @Override
    public ClansRequest region(Region region) {
        this.region = region;
        return this;
    }

    @Override
    public ClansRequest language(Language language) {
        this.language = language;
        return this;
    }

    /**
     * Sets the search text for this request.
     * <p>Replaces existing values! No validity check in this method!
     * If the search string is invalid, calling {@link #fetch()} will throw an Exception.</p>
     *
     * @param searchText the search string to set
     * @return the instance of this request
     */
    public ClansRequest search(String searchText) {
        this.searchText = searchText;
        return this;
    }

    /**
     * Sets the page number for this request.
     * <p>Replaces existing values. Minimum value is 1, maximum value is not defined.</p>
     *
     * @param page the new value for the request page
     * @return the instance of this request
     * @throws IllegalArgumentException If the value is less than 1.
     */
    public ClansRequest page(int page) {
        if (page > 0) {
            this.page = page;
            return this;
        } else {
            throw new IllegalArgumentException("Page must not be less than 1");
        }
    }

    /**
     * Executes a request and returns the result of the request.
     * <p>All requests are executed synchronous on this thread. It is safe to execute this in a new thread if it is required to be run asynchronous.</p>
     *
     * @return an instance of {@link Clans} that contains all requested player data. If the request fails, an empty instance is returned.
     * @throws IllegalArgumentException <ul>
     *                                  <li>If this method is called and region is null.</li>
     *                                  <li>If this method is called and search string is null.</li>
     *                                  <li>If this method is called and the length of the search string contains less than 2 characters-</li>
     *                                  </ul>
     */
    @Override
    public Clans fetch() {
        if (searchText == null || searchText.length() < 2 || region == null) {
            throw new IllegalArgumentException("Search text has to be at least 2 chars long and region must not be null");
        }
        String path = "/wows/clans/list/";
        String url = baseUrl(region, path, language) + FieldType.SEARCH + searchText + FieldType.PAGE + page;
        Clans result;
        SimpleRateLimiter.waitForPermit();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            result = GSON.fromJson(reader, Clans.class);
        } catch (Exception e) {
            LOG.error("An exception occured", e);
            result = new Clans();
        }
        return result;
    }
}
