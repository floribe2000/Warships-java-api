package de.floribe2000.warships_java.direct.encyclopedia;

import de.floribe2000.warships_java.direct.api.*;
import de.floribe2000.warships_java.direct.api.typeDefinitions.ConsumableType;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * A class to request details about different consumables from the game.
 * <p>Not all consumables are supported at the moment because the api does not provide information for all of them.</p>
 *
 * @author floribe2000
 * @since 0.2.12
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsumablesRequest extends AbstractRequest<ConsumablesRequest, Consumables> {

    /**
     * The server region for this request
     */
    private Region region = null;

    /**
     * The language for the api response
     */
    private Language language = null;

    /**
     * The type of consumables for this request
     */
    private ConsumableType type = null;

    /**
     * The page for the request
     */
    private int page_no = 1;

    /**
     * A possibly empty list of consumable ids for the request
     */
    private Set<Long> consumable_id = new HashSet<>();

    /**
     * Creates a new empty request of this class.
     *
     * @return an instance of this class
     */
    public static ConsumablesRequest createRequest() {
        return new ConsumablesRequest();
    }

    @Override
    public ConsumablesRequest apiBuilder(String instanceName) {
        setInstance(instanceName);
        return this;
    }

    @Override
    public ConsumablesRequest region(Region region) {
        this.region = region;
        return this;
    }

    @Override
    public ConsumablesRequest language(Language language) {
        this.language = language;
        return this;
    }

    /**
     * Defines the type of consumables for the request. As an optional field, this can be null.
     *
     * @param type the type of consumables for this request
     * @return the instance of this request
     */
    public ConsumablesRequest type(ConsumableType type) {
        this.type = type;
        return this;
    }

    /**
     * Defines the page of the request.
     *
     * @param page_no the page of the request, has to be 1 or more
     * @return the instance of this request
     * @throws IllegalArgumentException If the provided page number is less than 1.
     */
    public ConsumablesRequest page(int page_no) {
        if (page_no < 1) {
            throw new IllegalArgumentException("Page number has to be 1 or more.");
        }
        this.page_no = page_no;
        return this;
    }

    /**
     * Adds one or more consumable ids to the list of consumables to retrieve.
     * <p>The total amount of requested consumables must not exceed 100!</p>
     * <p>Will replace existing values.</p>
     *
     * @param consumable_id a collection of ids for this request
     * @return the instance of this request
     * @throws IllegalArgumentException If the size of the consumable_id collection exceeds the limit of 100.
     */
    public ConsumablesRequest consumable_id(Collection<Long> consumable_id) {
        Set<Long> tmp = new HashSet<>(consumable_id);
        if (tmp.size() > 100) {
            throw new IllegalArgumentException("Not more than 100 ids must be specified!");
        }
        this.consumable_id = tmp;
        return this;
    }

    /**
     * Adds one or more consumable ids to the list of consumables to retrieve.
     * <p>The total amount of requested consumables must not exceed 100!</p>
     * <p>Does not replace existing values.</p>
     *
     * @param consumableId the consumable ids to add
     * @return the instance of this request
     * @throws IllegalArgumentException If the amount of consumable ids would exceed 100 after adding the new elements.
     */
    public ConsumablesRequest addConsumable(Long... consumableId) {
        int newItems = 0;
        for (long l : consumableId) {
            if (!consumable_id.contains(l)) {
                newItems++;
            }
        }
        if (newItems + consumable_id.size() > 100) {
            throw new IllegalArgumentException("Not more than 100 ids must be specified!");
        }
        consumable_id.addAll(Arrays.asList(consumableId));
        return this;
    }

    /**
     * Executes a request and returns the result of the request.
     * <p>All requests are executed synchronous on this thread. It is safe to execute this in a new thread if it is required to be run asynchronous.</p>
     *
     * @return an instance of {@link Consumables} that contains all requested consumable data. If the request fails, an empty instance is returned.
     * @throws IllegalArgumentException If this method is called and region is null.
     */
    @Override
    protected Consumables fetch(String url) {
        return connect(url, Consumables.class, getLimiter());
    }

    @Override
    public String buildUrl() {
        if (region == null) {
            throw new IllegalArgumentException("Region must not be null.");
        }
        String path = "/wows/encyclopedia/consumables/";
        String type = this.type != null ? this.type.toString() : "";
        String page = page_no > 1 ? "&page_no=" + page_no : "";
        return baseUrl(region, path, language, getInstanceName()) + type + page;
    }
}
