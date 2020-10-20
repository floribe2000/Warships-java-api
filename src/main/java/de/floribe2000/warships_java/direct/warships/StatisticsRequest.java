package de.floribe2000.warships_java.direct.warships;

import de.floribe2000.warships_java.direct.api.AbstractRequest;
import de.floribe2000.warships_java.direct.api.IResponseFields;
import de.floribe2000.warships_java.direct.api.typeDefinitions.*;
import de.floribe2000.warships_java.direct.encyclopedia.Warships;
import de.floribe2000.warships_java.direct.encyclopedia.Warships.ShipEntry;
import de.floribe2000.warships_java.direct.encyclopedia.WarshipsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A class to create and execute requests to get details about the stats of single ships for a player.
 * <p>If the list of ships is not defined, statistics for all ships will be returned. If the list of ships is defined, statistics for up to 100 ships can be requested.</p>
 *
 * @author floribe2000, SirLefti
 */
//TODO allow use of all parameters
public class StatisticsRequest extends AbstractRequest<StatisticsRequest, Statistics> {

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
    private long accountId = 0;

    /**
     * The extra fields for this request
     */
    private Set<ExtraField> extraFields = new HashSet<>();

    /**
     * A set of up to 100 ship ids for this request
     */
    private Set<Long> shipIds = new HashSet<>();

    /**
     * A set of nations for the request
     */
    private Set<Nation> nations = new HashSet<>();

    /**
     * A set of ship types for the request
     */
    private Set<ShipType> shipTypes = new HashSet<>();

    /**
     * A set of ship categories for the request
     */
    private Set<ShipCategory> shipCategories = new HashSet<>();

    /**
     * A set of ship tiers for the request
     */
    private Set<Tier> shipTiers = new HashSet<>();

    public StatisticsRequest() {
    }

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

    @Override
    public StatisticsRequest apiBuilder(String instanceName) {
        setInstance(instanceName);
        return this;
    }

    /**
     * Sets the account id for this request.
     * <p>Replaces existing values!</p>
     *
     * @param accountId the new account id for this request
     * @return the instance of this request
     */
    public StatisticsRequest accountId(long accountId) {
        this.accountId = accountId;
        return this;
    }

    /**
     * Sets the set of extra fields.
     * <p>Replaces existing values! To add fields use {@link #addExtraField(ExtraField...)}.</p>
     *
     * @param extraFields the collection of {@link ExtraField ExtraFields} to set
     * @return the instance of this request
     */
    public StatisticsRequest extraFields(Collection<ExtraField> extraFields) {
        this.extraFields = new HashSet<>(extraFields);
        return this;
    }

    /**
     * Sets the set of extra fields.
     * <p>Replaces existing values! To add fields use {@link #addExtraField(ExtraField...)}.</p>
     *
     * @param extraFields the array of {@link ExtraField ExtraFields} to set
     * @return the instance of this request
     */
    public StatisticsRequest extraFields(ExtraField... extraFields) {
        this.extraFields = new HashSet<>(Arrays.asList(extraFields));
        return this;
    }

    /**
     * Adds an extra field to this request.
     * <p>Does not change existing values!</p>
     *
     * @param extraField the {@link ExtraField ExtraField} to add
     * @return the instance of this request
     */
    public StatisticsRequest addExtraField(ExtraField extraField) {
        this.extraFields.add(extraField);
        return this;
    }

    /**
     * Add extra fields to this request.
     * <p>Does not change existing values!</p>
     *
     * @param extraFields the {@link ExtraField ExtraFields} to add
     * @return the instance of this request
     */
    public StatisticsRequest addExtraField(Collection<ExtraField> extraFields) {
        this.extraFields.addAll(extraFields);
        return this;
    }

    /**
     * Add extra fields to this request.
     * <p>Does not change existing values!</p>
     *
     * @param extraFields the {@link ExtraField ExtraFields} to add
     * @return the instance of this request
     */
    public StatisticsRequest addExtraField(ExtraField... extraFields) {
        this.extraFields.addAll(Arrays.asList(extraFields));
        return this;
    }

    /**
     * Removes the given extra field from this request.
     *
     * @param extraField the {@link ExtraField ExtraField} to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeExtraField(ExtraField extraField) {
        this.extraFields.remove(extraField);
        return this;
    }

    /**
     * Removes the given extra fields from this request.
     *
     * @param extraFields the {@link ExtraField ExtraFields} to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeExtraFields(Collection<ExtraField> extraFields) {
        this.extraFields.removeAll(extraFields);
        return this;
    }

    /**
     * Removes the given extra fields from this request.
     *
     * @param extraFields the {@link ExtraField ExtraFields} to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeExtraFields(ExtraField... extraFields) {
        this.extraFields.removeAll(Arrays.asList(extraFields));
        return this;
    }

    /**
     * Sets the set of ship ids for this request.
     * <p>Replaces existing values! To add ships use {@link #addShipIds(Long...)} instead.</p>
     *
     * @param shipIds the collection of {@link Long shipIds} to set
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
     * Sets the set of ship ids for this request.
     * <p>Replaces existing values! To add ships use {@link #addShipIds(Long...)} instead.</p>
     *
     * @param shipIds the array of {@link Long shipIds} to set
     * @return the instance of this request
     * @throws IllegalStateException If the array contains more than 100 ids.
     */
    public StatisticsRequest shipIds(Long... shipIds) {
        if (shipIds.length <= 100) {
            this.shipIds = new HashSet<>(Arrays.asList(shipIds));
        } else {
            throw new IllegalStateException("There must be not more than 100 ship ids.");
        }
        return this;
    }

    /**
     * Adds a ship id to the request.
     * <p>Does not change existing values!</p>
     *
     * @param shipId the {@link Long shipId} to add
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
     * Adds given ship ids to the request.
     * <p>Does not change existing values!</p>
     *
     * @param shipIds the collection of {@link Long shipIds} to add
     * @return the instance of this request
     * @throws IllegalStateException If there would be set more than 100 ids for this request.
     */
    public StatisticsRequest addShipIds(Collection<Long> shipIds) {
        if (this.shipIds.size() + shipIds.size() <= 100) {
            this.shipIds.addAll(shipIds);
        } else {
            throw new IllegalStateException("There must be not more than 100 ship ids.");
        }
        return this;
    }

    /**
     * Adds given ship ids to the request.
     * <p>Does not change existing values!</p>
     *
     * @param shipIds the array {@link Long shipIds} to add
     * @return the instance of this request
     * @throws IllegalStateException If there would be set more than 100 ids for this request.
     */
    public StatisticsRequest addShipIds(Long... shipIds) {
        if (this.shipIds.size() + shipIds.length <= 100) {
            this.shipIds.addAll(Arrays.asList(shipIds));
        } else {
            throw new IllegalStateException("There must be not more than 100 ship ids.");
        }
        return this;
    }

    /**
     * Removes a ship id from the list of ids.
     *
     * @param shipId the {@link Long shipId} to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeShipId(long shipId) {
        this.shipIds.remove(shipId);
        return this;
    }

    /**
     * Removes given ship ids from the list of ids.
     *
     * @param shipIds the collection of {@link Long shipIds} to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeShipIds(Collection<Long> shipIds) {
        this.shipIds.removeAll(shipIds);
        return this;
    }

    /**
     * Removes given ship ids from the list of ids.
     *
     * @param shipIds the array of {@link Long shipIds} to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeShipIds(Long... shipIds) {
        this.shipIds.removeAll(Arrays.asList(shipIds));
        return this;
    }

    /**
     * Sets the nations to the request.
     * <p>Replaces existing values! To add nations use {@link #addNations(Nation...)} instead.</p>
     *
     * @param nations the collection of {@link Nation nations} to set
     * @return the instance of this request
     */
    public StatisticsRequest nations(Collection<Nation> nations) {
        this.nations.addAll(nations);
        return this;
    }

    /**
     * Sets the nations to the request.
     * <p>Replaces existing values! To add nations use {@link #addNations(Nation...)} instead.</p>
     *
     * @param nations the array of {@link Nation nations} to set
     * @return the instance of this request
     */
    public StatisticsRequest nations(Nation... nations) {
        this.nations.addAll(Arrays.asList(nations));
        return this;
    }

    /**
     * Adds one nation to the request.
     * <p>Does not change existing values!</p>
     *
     * @param nation the {@link Nation nation} to add
     * @return the instance of this request
     */
    public StatisticsRequest addNation(Nation nation) {
        this.nations.addAll(Arrays.asList(nation));
        return this;
    }

    /**
     * Adds one or more nations to the request.
     * <p>Does not change existing values!</p>
     *
     * @param nations the collection of {@link Nation nations} to add
     * @return the instance of this request
     */
    public StatisticsRequest addNations(Collection<Nation> nations) {
        this.nations.addAll(nations);
        return this;
    }

    /**
     * Adds one or more nations to the request.
     * <p>Does not change existing values!</p>
     *
     * @param nations the array of {@link Nation nations} to add
     * @return the instance of this request
     */
    public StatisticsRequest addNations(Nation... nations) {
        this.nations.addAll(Arrays.asList(nations));
        return this;
    }

    /**
     * Removes a nation from the set of nations.
     *
     * @param nation the {@link Nation nation} to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeNation(Nation nation) {
        this.nations.remove(nation);
        return this;
    }

    /**
     * Removes given nations from the set of nations.
     *
     * @param nations the collection of {@link Nation nations} to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeNations(Collection<Nation> nations) {
        this.nations.removeAll(nations);
        return this;
    }

    /**
     * Removes given nations from the set of nations.
     *
     * @param nations the array of {@link Nation nations} to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeNations(Nation... nations) {
        this.nations.removeAll(Arrays.asList(nations));
        return this;
    }

    /**
     * Adds given ship types to the request.
     * <p>Replaces existing values! To add ship types use {@link #addShipTypes(ShipType...)} instead.</p>
     *
     * @param types the collection of {@link ShipType types} to set
     * @return the instance of this request
     */
    public StatisticsRequest shipTypes(Collection<ShipType> types) {
        this.shipTypes.addAll(types);
        return this;
    }

    /**
     * Adds given ship types to the request.
     * <p>Replaces existing values! To add ship types use {@link #addShipTypes(ShipType...)} instead.</p>
     *
     * @param types the array of {@link ShipType types} to set
     * @return the instance of this request
     */
    public StatisticsRequest shipTypes(ShipType... types) {
        this.shipTypes.addAll(Arrays.asList(types));
        return this;
    }

    /**
     * Adds one or more ship types to the request.
     * <p>Does not change existing values!</p>
     *
     * @param types the colleection of {@link ShipType types} to add
     * @return the instance of this request
     */
    public StatisticsRequest addShipTypes(Collection<ShipType> types) {
        this.shipTypes.addAll(types);
        return this;
    }

    /**
     * Adds one or more ship types to the request.
     * <p>Does not change existing values!</p>
     *
     * @param types the array of {@link ShipType types} to add
     * @return the instance of this request
     */
    public StatisticsRequest addShipTypes(ShipType... types) {
        this.shipTypes.addAll(Arrays.asList(types));
        return this;
    }

    /**
     * Adds one ship type to the request.
     * <p>Does not change existing values!</p>
     *
     * @param type the {@link ShipType type} to add
     * @return the instance of this request
     */
    public StatisticsRequest addShipType(ShipType type) {
        this.shipTypes.add(type);
        return this;
    }

    /**
     * Remove given ship types to the request.
     * <p>Does not change existing values!</p>
     *
     * @param types the collection of {@link ShipType types} to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeShipTypes(Collection<ShipType> types) {
        this.shipTypes.removeAll(types);
        return this;
    }

    /**
     * Remove given ship types to the request.
     * <p>Does not change existing values!</p>
     *
     * @param types the array of {@link ShipType types} to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeShipTypes(ShipType... types) {
        this.shipTypes.removeAll(Arrays.asList(types));
        return this;
    }

    /**
     * Remove given ship type to the request.
     * <p>Does not change existing values!</p>
     *
     * @param type the {@link ShipType type} to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeShipType(ShipType type) {
        this.shipTypes.remove(type);
        return this;
    }

    /**
     * Adds one or more ship tiers to the request.
     * <p>Replaces existing values! To add ship tiers use {@link #addTiers(Tier...)} instead.</p>
     *
     * @param tiers the collection of {@link Tier tiers} to set
     * @return the instance of this request
     */
    public StatisticsRequest tiers(Collection<Tier> tiers) {
        this.shipTiers = new HashSet<>(tiers);
        return this;
    }

    /**
     * Adds one or more ship tiers to the request.
     * <p>Replaces existing values! To add ship tiers use {@link #addTiers(Tier...)} instead.</p>
     *
     * @param tiers the array of {@link Tier tiers} to set
     * @return the instance of this request
     */
    public StatisticsRequest tiers(Tier... tiers) {
        this.shipTiers = new HashSet<>(Arrays.asList(tiers));
        return this;
    }

    /**
     * Adds one or more ship tiers to the request.
     * <p>Does not change existing values!</p>
     *
     * @param tiers the collection of {@link Tier tiers} to add
     * @return the instance of this request
     */
    public StatisticsRequest addTiers(Collection<Tier> tiers) {
        this.shipTiers.addAll(tiers);
        return this;
    }

    /**
     * Adds one or more ship tiers to the request.
     * <p>Does not change existing values!</p>
     *
     * @param tiers the array of {@link Tier tiers} to add
     * @return the instance of this request
     */
    public StatisticsRequest addTiers(Tier... tiers) {
        this.shipTiers.addAll(Arrays.asList(tiers));
        return this;
    }

    /**
     * Adds one ship tiers to the request.
     * <p>Does not change existing values!</p>
     *
     * @param tier the {@link Tier tier} to add
     * @return the instance of this request
     */
    public StatisticsRequest addTier(Tier tier) {
        this.shipTiers.add(tier);
        return this;
    }

    /**
     * Removes one or more ship tiers to the request.
     *
     * @param tiers the collection of {@link Tier tiers} to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeTiers(Collection<Tier> tiers) {
        this.shipTiers.removeAll(tiers);
        return this;
    }

    /**
     * Removes one or more ship tiers to the request.
     *
     * @param tiers the array of {@link Tier tiers} to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeTiers(Tier... tiers) {
        this.shipTiers.removeAll(Arrays.asList(tiers));
        return this;
    }

    /**
     * Removes one ship tiers to the request.
     *
     * @param tier the {@link Tier tier} to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeTier(Tier tier) {
        this.shipTiers.remove(tier);
        return this;
    }

    /**
     * Sets the ship categories to the request.
     * <p>Replaces existing values! To add ship categories use {@link #addCategories(ShipCategory...)} instead.</p>
     *
     * @param categories the collection of {@link ShipCategory categories} to set
     * @return the instance of this request
     */
    public StatisticsRequest categories(Collection<ShipCategory> categories) {
        this.shipCategories = new HashSet<>(categories);
        return this;
    }

    /**
     * Sets the ship categories to the request.
     * <p>Replaces existing values! To add ship categories use {@link #addCategories(ShipCategory...)} instead.</p>
     *
     * @param categories the array of {@link ShipCategory categories} to set
     * @return the instance of this request
     */
    public StatisticsRequest categories(ShipCategory... categories) {
        this.shipCategories = new HashSet<>(Arrays.asList(categories));
        return this;
    }

    /**
     * Adds one or more ship categories to the request.
     * <p>Does not change existing values!</p>
     *
     * @param categories the collection of {@link ShipCategory categories} to add
     * @return the instance of this request
     */
    public StatisticsRequest addCategories(Collection<ShipCategory> categories) {
        this.shipCategories.addAll(categories);
        return this;
    }

    /**
     * Adds one or more ship categories to the request.
     * <p>Does not change existing values!</p>
     *
     * @param categories the array of {@link ShipCategory categories} to add
     * @return the instance of this request
     */
    public StatisticsRequest addCategories(ShipCategory... categories) {
        this.shipCategories.addAll(Arrays.asList(categories));
        return this;
    }

    /**
     * Adds one ship category to the request.
     * <p>Does not change existing values!</p>
     *
     * @param category the {@link ShipCategory category} to add
     * @return the instance of this request
     */
    public StatisticsRequest addCategory(ShipCategory category) {
        this.shipCategories.add(category);
        return this;
    }

    /**
     * Removes one or more ship categories to the request.
     *
     * @param categories the collection of {@link ShipCategory categories} to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeCategories(Collection<ShipCategory> categories) {
        this.shipCategories.removeAll(categories);
        return this;
    }

    /**
     * Removes one or more ship categories to the request.
     * <p>Does not change existing values!</p>
     *
     * @param categories the array of {@link ShipCategory categories} to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeCategories(ShipCategory... categories) {
        this.shipCategories.removeAll(Arrays.asList(categories));
        return this;
    }

    /**
     * Removes one ship category to the request.
     * <p>Does not change existing values!</p>
     *
     * @param category the {@link ShipCategory category} to remove
     * @return the instance of this request
     */
    public StatisticsRequest removeCategory(ShipCategory category) {
        this.shipCategories.remove(category);
        return this;
    }

    private Set<Long> getShipIds(Set<Long> baseShipSet, Set<Nation> filterNation, Set<ShipType> filterShipType, Set<ShipCategory> filterShipCategory, Set<Tier> filterTier) {
        WarshipsRequest request = WarshipsRequest.createRequest().region(region).nation(filterNation).shipType(filterShipType).shipCategory(filterShipCategory).tier(filterTier);
        Warships response;
        Set<Long> ships = new HashSet<>();

        int pageNo = 1;
        do {
            response = request.pageNo(pageNo).fetch();
            ships.addAll(response.getData().values().stream().map(ShipEntry::getShip_id).collect(Collectors.toSet()));
            pageNo++;
        } while (response.getMeta().getPage_total() >= pageNo);

        if (baseShipSet != null && baseShipSet.size() > 0) {
            ships.retainAll(baseShipSet);
        }
        return ships;
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
    protected Statistics fetch(String url) {
        //Statistics result;
//        SimpleRateLimiter.waitForPermit();
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
//            result = GSON.fromJson(reader, Statistics.class);
//        } catch (Exception e) {
//            LOG.error("An exception occured", e);
//            result = new Statistics();
//        }
        return connect(url, Statistics.class, getLimiter());
    }

    @Override
    public String buildUrl() {
        if (region == null || shipIds.size() > 100 || accountId < 500000000) {
            throw new IllegalArgumentException("Region must not be null and the number of clans must be between 1 and 100.");
        }
        String path = "/wows/ships/stats/";
        String extra = buildFieldString(FieldType.EXTRA, extraFields);
        String ships = shipIds.size() == 0 ? "" : FieldType.SHIP_ID + shipIds.stream().sequential().map(Objects::toString).collect(Collectors.joining(","));
        return baseUrl(region, path, language, getInstanceName()) + FieldType.ACCOUNT_ID + accountId + extra + ships;
    }

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

        ExtraField(String key) {
            this.key = key;
        }

        @Override
        public String retrieveKey() {
            return key;
        }
    }
}
