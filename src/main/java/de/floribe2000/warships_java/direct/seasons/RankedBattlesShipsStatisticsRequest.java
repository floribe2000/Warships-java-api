package de.floribe2000.warships_java.direct.seasons;

import de.floribe2000.warships_java.direct.api.AbstractRequest;
import de.floribe2000.warships_java.direct.api.typeDefinitions.FieldType;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RankedBattlesShipsStatisticsRequest extends AbstractRequest<RankedBattlesShipsStatisticsRequest, RankedBattlesShipsStatistics> implements IRequestAction<RankedBattlesShipsStatistics> {

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
     * The set of season IDs for this request
     */
    private Set<Integer> seasonIds = new HashSet<>();

    /**
     * The set of season IDs for this request
     */
    private Set<Integer> accountIds = new HashSet<>();

    /**
     * The set of season IDs for this request
     */
    private Set<Long> shipIds = new HashSet<>();

    @Override
    public RankedBattlesShipsStatisticsRequest region(Region region) {
        this.region = region;
        return this;
    }

    @Override
    public RankedBattlesShipsStatisticsRequest language(Language language) {
        this.language = language;
        return this;
    }

    public RankedBattlesShipsStatisticsRequest seasonIds(Collection<Integer> seasonIds) {
        if (seasonIds == null || seasonIds.size() > 100 || seasonIds.size() < 1) {
            throw new IllegalArgumentException(
                    "The collection must not be null and have between 1 and 100 entries");
        }
        this.seasonIds = new HashSet<>(seasonIds);
        return this;
    }

    public RankedBattlesShipsStatisticsRequest addSeason(int seasonId) {
        if (this.seasonIds.size() < 100) {
            this.seasonIds.add(seasonId);
        }
        return this;
    }

    public RankedBattlesShipsStatisticsRequest accountIds(Collection<Integer> accountIds) {
        if (accountIds == null || accountIds.size() > 100 || accountIds.size() < 1) {
            throw new IllegalArgumentException(
                    "The collection must not be null and have between 1 and 100 entries");
        }
        this.accountIds = new HashSet<>(accountIds);
        return this;
    }

    public RankedBattlesShipsStatisticsRequest addAccountId(int accountId) {
        if (this.accountIds.size() < 100) {
            this.accountIds.add(accountId);
        }
        return this;
    }

    public RankedBattlesShipsStatisticsRequest shipIds(Collection<Long> shipIds) {
        if (shipIds == null || shipIds.size() > 100 || shipIds.size() < 1) {
            throw new IllegalArgumentException(
                    "The collection must not be null and have between 1 and 100 entries");
        }
        this.shipIds = new HashSet<>(shipIds);
        return this;
    }

    public RankedBattlesShipsStatisticsRequest addShip(long shipId) {
        if (this.shipIds.size() < 100) {
            this.shipIds.add(shipId);
        }
        return this;
    }

    public static RankedBattlesShipsStatisticsRequest createRequest() {
        return new RankedBattlesShipsStatisticsRequest();
    }

    /**
     * Executes a request and returns the result of the request.
     *
     * @return an instance of {@link RankedBattlesShipsStatistics} that contains all requested data. If the
     * request fails, an empty instance is returned.
     * @throws IllegalArgumentException <ul>
     *                                  <li>If this method is called and region is null.</li>
     *                                  </ul>
     */
    @Override
    protected RankedBattlesShipsStatistics fetch(String url) {
        return connect(url, RankedBattlesShipsStatistics.class, getLimiter());
    }

    @Override
    public RankedBattlesShipsStatisticsRequest apiBuilder(String instanceName) {
        setInstance(instanceName);
        return this;
    }

    @Override
    public String buildUrl() {
        if (region == null) {
            throw new IllegalArgumentException("The region has to be set");
        }
        if (accountIds.isEmpty()) {
            throw new IllegalArgumentException("At least one account ID has to be set");
        }
        String path = "/wows/seasons/shipstats/";
        String seasons = seasonIds.stream().sequential().map(Objects::toString).collect(Collectors.joining(", "));
        String ships = shipIds.stream().sequential().map(Objects::toString).collect(Collectors.joining(", "));
        String accounts = accountIds.stream().sequential().map(Objects::toString).collect(Collectors.joining(", "));
        return baseUrl(region, path, language, getInstanceName()) + FieldType.SEASON_ID + seasons + FieldType.SHIP_ID + ships + FieldType.ACCOUNT_ID + accounts;
    }
}
