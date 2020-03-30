package de.floribe2000.warships_java.warships;

import de.floribe2000.warships_java.api.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A class to create and execute requests to get details about the stats of single ships for a player.
 * <p>If the list of ships is not defined, statistics for all ships will be returned. If the list of ships is defined, statistics for up to 100 ships can be requested.</p>
 *
 * @author floribe2000
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StatisticsRequest implements IRequestAction<Statistics>, IRequest<StatisticsRequest> {

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
     * The account id of the player for this request
     */
    private int accountId = 0;

    /**
     * The extra fields for this request
     */
    private Set<ExtraField> extraFields = new HashSet<>();

    /**
     * A set of up to 100 ship ids for this request
     */
    private Set<Long> shipIds = new HashSet<>();

    /**
     * Creates a new empty request of this class.
     *
     * @return an instance of this class
     */
    public static StatisticsRequest createRequest() {
        return new StatisticsRequest();
    }

    @Override
    public StatisticsRequest region(Region region) {
        this.region = region;
        return this;
    }

    @Override
    public StatisticsRequest language(Language language) {
        this.language = language;
        return this;
    }

    /**
     * Sets the account id for this request.
     * <p>Replaces existing values!</p>
     *
     * @param accountId the new account id for this request
     * @return the instance of this request
     */
    public StatisticsRequest accountId(int accountId) {
        this.accountId = accountId;
        return this;
    }

    /**
     * Sets the set of extra fields.
     * <p>Replaces existing values! To add fields use {@link #addExtraField(ExtraField...)}.</p>
     *
     * @param extraFields the collection of {@link ExtraField ExtraFields}
     * @return the instance of this request
     */
    public StatisticsRequest extraFields(Collection<ExtraField> extraFields) {
        this.extraFields = new HashSet<>(extraFields);
        return this;
    }

    /**
     * Adds an extra field to this request.
     * <p>Does not change existing values!</p>
     *
     * @param extraFields the extra fields to add
     * @return the instance of this request
     */
    public StatisticsRequest addExtraField(ExtraField... extraFields) {
        this.extraFields.addAll(Arrays.asList(extraFields));
        return this;
    }

    /**
     * Removes the given extra fields from this request.
     *
     * @param extraFields the extra fields to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeExtraField(ExtraField... extraFields) {
        this.extraFields.removeAll(Arrays.asList(extraFields));
        return this;
    }

    /**
     * Sets the set of ship ids for this request.
     * <p>Replaces existing values! To add ships use {@link #addShipId(long shipId)} instead.</p>
     *
     * @param shipIds the collection of ship ids
     * @return the instance of this request
     * @throws IllegalStateException If the collection contains more than 100 ids.
     */
    public StatisticsRequest shipIds(Collection<Long> shipIds) {
        if (shipIds.size() <= 100) {
            this.shipIds = new HashSet<>(shipIds);
        } else {
            throw new IllegalStateException("There must be not more than 100 ship ids.");
        }
        return this;
    }

    /**
     * Adds a ship id to the request.
     * <p>Existing values are not changed.</p>
     *
     * @param shipId the ship id to add
     * @return the instance of this request
     * @throws IllegalStateException If there are already 100 ids set for this request.
     */
    public StatisticsRequest addShipId(long shipId) {
        if (shipIds.size() < 100) {
            shipIds.add(shipId);
        } else {
            throw new IllegalStateException("There must be not more than 100 ship ids.");
        }
        return this;
    }

    /**
     * Removes a ship id from the list of ids.
     *
     * @param shipId the id to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeShipId(long shipId) {
        this.shipIds.remove(shipId);
        return this;
    }

    /**
     * Executes a request and returns the result of the request.
     * <p>All requests are executed synchronous on this thread. It is safe to execute this in a new thread if it is required to be run asynchronous.</p>
     *
     * @return an instance of {@link Statistics} that contains all requested player data. If the request fails, an empty instance is returned.
     * @throws IllegalArgumentException <ul>
     *                                  <li>If this method is called and region is null.</li>
     *                                  <li>If this method is called and the list of ship ids contains more than 100 entries.</li>
     *                                  <li>If the provided account id is not within the range of valid account ids.</li>
     *                                  </ul>
     */
    @Override
    public Statistics fetch() {
        if (region == null || shipIds.size() > 100 || accountId < 500000000) {
            throw new IllegalArgumentException("Region must not be null and the number of clans must be between 1 and 100.");
        }
        String path = "/wows/ships/stats/";
        String extra = buildFieldString(FieldType.EXTRA, extraFields);
        String ships = shipIds.size() == 0 ? "" : FieldType.SHIP_ID + shipIds.stream().sequential().map(Objects::toString).collect(Collectors.joining(","));
        String url = baseUrl(region, path, language) + FieldType.ACCOUNT_ID + accountId + extra + ships;
        //Statistics result;
//        SimpleRateLimiter.waitForPermit();
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
//            result = GSON.fromJson(reader, Statistics.class);
//        } catch (Exception e) {
//            LOG.error("An exception occured", e);
//            result = new Statistics();
//        }
        return connect(url, Statistics.class);
    }

    @AllArgsConstructor
    public enum ExtraField implements IResponseFields {
        OPER_DIV("oper_div"),
        OPER_DIV_HARD("oper_div_hard"),
        OPER_SOLO("oper_solo"),
        PVE("pve"),
        PVE_DIV2("pve_div2"),
        PVE_DIV3("pve_div3"),
        PVE_SOLO("pve_solo"),
        PVP_DIV2("pvp_div2"),
        PVP_DIV3("pvp_div3"),
        PVP_SOLO("pvp_solo"),
        RANK_DIV2("rank_div2"),
        RANK_DIV3("rank_div3"),
        RANK_SOLO("rank_solo");

        private String key;

        @Override
        public String retrieveKey() {
            return key;
        }
    }
}
