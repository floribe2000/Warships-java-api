package de.floribe2000.warships_java.encyclopedia;

import de.floribe2000.warships_java.api.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

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

    private Set<Long> shipIds = new HashSet<>();

    private int pageNo = 1;

    private int limit = 100;

    private Set<String> nations = new HashSet<>();

    @Override
    public WarshipsRequest apiBuilder(String instanceName) {
        setInstance(instanceName);
        return this;
    }

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

    public WarshipsRequest pageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public WarshipsRequest limit(int limit) {
        if (limit <= 100 && limit > 0) {
            this.limit = limit;
            return this;
        } else {
            throw new IllegalArgumentException("Limit must be within 1 and 100.");
        }
    }

    public WarshipsRequest shipIds(Collection<Long> shipIds) {
        if (shipIds.size() > 0 && shipIds.size() <= 100) {
            this.shipIds = new HashSet<>(shipIds);
            return this;
        } else {
            throw new IllegalArgumentException("The size of the provided collection must be within 1 and 100.");
        }
    }

    public WarshipsRequest addShipId(Long... shipId) {
        Set<Long> newEntries = Arrays.stream(shipId).filter(i -> !this.shipIds.contains(i)).collect(Collectors.toSet());
        if (shipIds.size() + newEntries.size() <= 100) {
            shipIds.addAll(newEntries);
            return this;
        } else {
            throw new IllegalArgumentException("You cannot specify more than 100 ship ids.");
        }
    }

    public WarshipsRequest nation(String... nations) {
        this.nations.addAll(Arrays.asList(nations));
        return this;
    }

    @Override
    public Warships fetch() {
        if (region == null) {
            throw new IllegalStateException("Region must not be null.");
        }
        String path = "/wows/encyclopedia/ships/";
        String nations = this.nations.isEmpty() ? "" : FieldType.NATION + String.join(",", this.nations);
        String ships = this.shipIds.isEmpty() ? "" : FieldType.SHIP_ID + shipIds.stream().map(Objects::toString).collect(Collectors.joining(","));
        String url = baseUrl(region, path, language, getInstanceName()) + ships + nations +
                FieldType.PAGE + pageNo + FieldType.LIMIT + limit;
        return connect(url, Warships.class, getLimiter());
    }
}
