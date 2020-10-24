package de.floribe2000.warships_java.direct.clans;

import de.floribe2000.warships_java.direct.api.AbstractRequest;
import de.floribe2000.warships_java.direct.api.IResponseFields;
import de.floribe2000.warships_java.direct.api.typeDefinitions.FieldType;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A class to create and execute requests to retrieve details of selected clans
 * <p>Up to 100 clans can be retrieved in one request. For more clans you have to use this request multiple times.</p>
 * <p>It is possible to just replace the list of clan ids before calling {@link #fetch()} so you don't have to create a new request instance every time.</p>
 *
 * @author floribe2000
 */
public class ClanDetailsRequest extends AbstractRequest<ClanDetailsRequest, ClanDetails> {

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
     * The set of clan ids for this request
     */
    private Set<Long> clanIds = new HashSet<>();

    /**
     * The extra field for this request
     */
    private boolean extra = false;

    private ClanDetailsRequest() {
    }

    /**
     * Creates a new empty request of this class.
     *
     * @return an instance of this class
     */
    public static ClanDetailsRequest createRequest() {
        return new ClanDetailsRequest();
    }

    @Override
    public ClanDetailsRequest region(Region region) {
        this.region = region;
        return this;
    }

    @Override
    public ClanDetailsRequest language(Language language) {
        this.language = language;
        return this;
    }

    @Override
    public ClanDetailsRequest apiBuilder(String instanceName) {
        setInstance(instanceName);
        return this;
    }

    /**
     * Adds an extra field to the request.
     * <p>Since there is only one extra field available for this request, this method can also be used to toggle this field.
     * To remove it, simply call the method with null as parameter. To add it, call the method with {@link ExtraField#MEMBERS} as parameter.</p>
     *
     * @param field the field to add
     * @return the instance of this request
     */
    public ClanDetailsRequest extra(ExtraField field) {
        extra = field != null; //Since there is only one field, there is no need to store that field
        return this;
    }

    /**
     * Adds a collection of clan ids to this request.
     * <p>Replaces existing entries!</p>
     *
     * @param clanIds the collection of clan ids to add
     * @return the instance of this request
     * @throws IllegalArgumentException <ul>
     *                                  <li>If the collection of clan ids is null or empty.</li>
     *                                  <li>If the collection of clan ids contains more than 100 entries.</li>
     *                                  </ul>
     */
    public ClanDetailsRequest clanIds(Collection<Long> clanIds) {
        if (clanIds == null || clanIds.size() > 100 || clanIds.size() < 1) {
            throw new IllegalArgumentException("The collection must not be null and have between 1 and 100 entries");
        }
        this.clanIds = new HashSet<>(clanIds);
        return this;
    }

    /**
     * Adds a single clan to the request.
     * <p>Doesn't change existing entries. If the list is full, nothing will happen.</p>
     *
     * @param clanId the clan id to add
     * @return the instance of this request
     */
    public ClanDetailsRequest addClan(long clanId) {
        if (this.clanIds.size() < 100) {
            this.clanIds.add(clanId);
        }
        return this;
    }

    /**
     * Executes a request and returns the result of the request.
     * <p>All requests are executed synchronous on this thread. It is safe to execute this in a new thread if it is required to be run asynchronous.</p>
     *
     * @return an instance of {@link ClanDetails} that contains all requested player data. If the request fails, an empty instance is returned.
     * @throws IllegalArgumentException <ul>
     *                                  <li>If this method is called and region is null.</li>
     *                                  <li>If this method is called and the list of clan ids is empty.</li>
     *                                  <li>If this method is called and the list of clan ids contains more than 100 entries.</li>
     *                                  </ul>
     */
    @Override
    protected ClanDetails fetch(String url) {
//        if (region == null || clanIds.size() < 1 || clanIds.size() > 100) {
//            throw new IllegalArgumentException("Region must not be null and the number of clans must be between 1 and 100.");
//        }
//        String path = "/wows/clans/info/";
//        String clans = clanIds.stream().sequential().map(Objects::toString).collect(Collectors.joining(","));
//        String extra = !this.extra ? "" : FieldType.EXTRA + ExtraField.MEMBERS.key;
//        String url = baseUrl(region, path, language, getInstanceName()) + FieldType.CLAN + clans + extra;
//        ClanDetails result;
//        SimpleRateLimiter.waitForPermit();
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
//            result = GSON.fromJson(reader, ClanDetails.class);
//        } catch (Exception e) {
//            LOG.error("An exception occured", e);
//            result = new ClanDetails();
//        }
        return connect(url, ClanDetails.class, getLimiter());
    }

    @Override
    public String buildUrl() {
        if (region == null || clanIds.size() < 1 || clanIds.size() > 100) {
            throw new IllegalArgumentException("Region must not be null and the number of clans must be between 1 and 100.");
        }
        String path = "/wows/clans/info/";
        String clans = clanIds.stream().sequential().map(Objects::toString).collect(Collectors.joining(","));
        String extra = !this.extra ? "" : FieldType.EXTRA + ExtraField.MEMBERS.key;
        return baseUrl(region, path, language, getInstanceName()) + FieldType.CLAN + clans + extra;
    }

    /**
     * The extra fields for this request.
     * <p>In this case, there is only a single field available.</p>
     */
    public enum ExtraField implements IResponseFields {
        /**
         * If selected includes a list of all clan members with their {@link ClanRole}, join date and account id.
         */
        MEMBERS("members");

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
