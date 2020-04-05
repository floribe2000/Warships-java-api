package de.floribe2000.warships_java.encyclopedia;

import de.floribe2000.warships_java.api.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A class to create requests to get information about ships.
 * <p>Up to 100 ships can be retrieved per request, depending on the defined parameters less entries can be returned.</p>
 * <p>Details about the total amount of hits and the total number of pages can be retrieved by analyzing the {@link Meta Meta} object of the api response
 * returned by {@link #fetch()}.</p>
 *
 * @author floribe2000
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WarshipsRequest extends AbstractRequest<WarshipsRequest> implements IRequestAction<Warships> {

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
     * A set of ship Ids for the request. May be empty, maximum size is 100
     */
    private Set<Long> shipIds = new HashSet<>();

    /**
     * The page of the response
     */
    private int pageNo = 1;

    /**
     * The limit of entries per page of the response. Maximum value is 100.
     */
    private int limit = 100;

    /**
     * A set of nations for the request
     */
    private Set<String> nations = new HashSet<>();

    @Override
    public WarshipsRequest apiBuilder(String instanceName) {
        setInstance(instanceName);
        return this;
    }

    /**
     * Creates a new empty request of this class.
     *
     * @return an instance of this class
     */
    public static WarshipsRequest createRequest() {
        return new WarshipsRequest();
    }

    @Override
    public WarshipsRequest region(Region region) {
        this.region = region;
        return this;
    }

    @Override
    public WarshipsRequest language(Language language) {
        this.language = language;
        return this;
    }

    /**
     * Defines the page number of the response. Valid values depend on the amount of results for a request
     *
     * @param pageNo the number of the page
     * @return the instance of this request
     */
    public WarshipsRequest pageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    /**
     * Sets the limit of results per page for the request.
     *
     * @param limit the new limit, must be within 1 and 100
     * @return the instance of this request
     */
    public WarshipsRequest limit(int limit) {
        if (limit <= 100 && limit > 0) {
            this.limit = limit;
            return this;
        } else {
            throw new IllegalArgumentException("Limit must be within 1 and 100.");
        }
    }

    /**
     * Sets the ship ids for the request. A maximum of 100 ship ids may be set.
     * <p>Replaces existing values!</p>
     *
     * @param shipIds the collection of ship ids for the request
     * @return the instance of this request
     */
    public WarshipsRequest shipIds(Collection<Long> shipIds) {
        if (shipIds.size() > 0 && shipIds.size() <= 100) {
            this.shipIds = new HashSet<>(shipIds);
            return this;
        } else {
            throw new IllegalArgumentException("The size of the provided collection must be within 1 and 100.");
        }
    }

    /**
     * Adds one or more ship ids to the request. If the total number of ship ids after adding the new ones would be bigger than 100, an exception is thrown.
     * <p>Doesn't change existing values.</p>
     *
     * @param shipId the ship ids to add
     * @return the instance of this request
     * @throws IllegalArgumentException If the total number of ship ids after adding the new ones is bigger than 100.
     */
    public WarshipsRequest addShipId(Long... shipId) {
        Set<Long> newEntries = Arrays.stream(shipId).filter(i -> !this.shipIds.contains(i)).collect(Collectors.toSet());
        if (shipIds.size() + newEntries.size() <= 100) {
            shipIds.addAll(newEntries);
            return this;
        } else {
            throw new IllegalArgumentException("You cannot specify more than 100 ship ids.");
        }
    }

    /**
     * Adds one or more nations to the request.
     * <p>Doesn't replace existing values!</p>
     *
     * @param nations the nations to add
     * @return the instance of this request
     */
    public WarshipsRequest nation(String... nations) {
        this.nations.addAll(Arrays.asList(nations));
        return this;
    }

    /**
     * Executes a request and returns the result of the request.
     * <p>All requests are executed synchronous on this thread. It is safe to execute this in a new thread if it is required to be run asynchronous.</p>
     *
     * @return an instance of {@link Warships} that contains all requested player data. If the request fails, an empty instance is returned.
     * @throws IllegalArgumentException If this method is called and region is null.
     */
    @Override
    public Warships fetch() {
        if (region == null) {
            throw new IllegalArgumentException("Region must not be null.");
        }
        String path = "/wows/encyclopedia/ships/";
        String nations = this.nations.isEmpty() ? "" : FieldType.NATION + String.join(",", this.nations);
        String ships = this.shipIds.isEmpty() ? "" : FieldType.SHIP_ID + shipIds.stream().map(Objects::toString).collect(Collectors.joining(","));
        String url = baseUrl(region, path, language, getInstanceName()) + ships + nations +
                FieldType.PAGE + pageNo + FieldType.LIMIT + limit;
        return connect(url, Warships.class, getLimiter());
    }
}
