package de.floribe2000.warships_java.account;

import de.floribe2000.warships_java.api.*;
import de.floribe2000.warships_java.requests.SimpleRateLimiter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A class to create and execute requests to retrieve personal data for one or more players from the api.
 * <p>By default only pvp statistics and basic player details are retrieved, more fields can be specified by using
 * the {@link #extraFields(ExtraField...)} or {@link #addExtraField(ExtraField...)} methods.</p>
 *
 * <p>The request is executed by calling the {@link #fetch()} method, for details see the javadoc of those methods.</p>
 *
 * @author floribe2000
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayersPersonalDataFullRequest implements IRequestAction<PlayersPersonalDataFull>, IAccountRequest<PlayersPersonalDataFullRequest> {

    /**
     * A Logger instance used to log events of this class
     */
    private final Logger LOG = LoggerFactory.getLogger(getClass().getSimpleName());

    /**
     * The region for the request. Must not be null when fetch ist called.
     */
    private Region region = null;

    /**
     * The language for the api response
     */
    private Language language = null;

    /**
     * A set of accound ids for the request.
     */
    private Set<Integer> accountIds = new HashSet<>();

    /**
     * A set of extra fields that should be added to the request and will be retrieved from the api.
     */
    private Set<ExtraField> extraFields = null;

    /**
     * Creates a new empty request instance of this class.
     *
     * @return a new instance of this class
     */
    public static PlayersPersonalDataFullRequest createRequest() {
        return new PlayersPersonalDataFullRequest();
    }

    @Override
    public PlayersPersonalDataFullRequest region(Region region) {
        this.region = region;
        return this;
    }

    @Override
    public PlayersPersonalDataFullRequest language(Language language) {
        this.language = language;
        return this;
    }

    /**
     * Adds one or more fields to the {@link #extraFields} set.
     * <p>Existing fields won't be changed!</p>
     *
     * @param fields the fields to add to the request's extra fields
     * @return the instance of this request
     */
    public PlayersPersonalDataFullRequest addExtraField(ExtraField... fields) {
        if (extraFields == null) {
            extraFields = new HashSet<>();
        }
        extraFields.addAll(Arrays.asList(fields));
        return this;
    }

    /**
     * Adds an account id to the list of account ids.
     * <p>Existing ids won't be changed!
     * If the limit is reached, the id won't be added to the request and a logging call is triggered.</p>
     *
     * @param accountId the id to add
     * @return the instance of this request
     */
    public PlayersPersonalDataFullRequest addAccountId(int accountId) {
        if (this.accountIds.size() < 100) {
            this.accountIds.add(accountId);
        } else {
            LOG.warn("Skipping account id addition. Reason: Limit reached (Limit: 100)");
        }
        return this;
    }

    /**
     * Sets the list of account ids.
     * <p>Warning: the existing ids are replaced!</p>
     *
     * @param accountIds the ids that should be added
     * @return the instance of this request
     * @throws IllegalArgumentException If the size of the set exceeds the limit of 100 ids
     */
    public PlayersPersonalDataFullRequest accountIds(Set<Integer> accountIds) {
        if (accountIds.size() > 100) {
            throw new IllegalArgumentException("The size of the set must not exceed 100");
        }
        this.accountIds = accountIds;
        return this;
    }

    /**
     * Adds one or more {@link ExtraField extra fields} to the request.
     * <p>Existing fields won't be changed.</p>
     *
     * @param extraFields the fields to add to the request
     * @return the instance of this request
     */
    public PlayersPersonalDataFullRequest extraFields(ExtraField... extraFields) {
        this.extraFields.addAll(Arrays.asList(extraFields));
        return this;
    }

    /**
     * Executes a request and returns the result of the request.
     * <p>All requests are executed synchronous on this thread. It is safe to execute this in a new thread if it is required to be run asynchronous.</p>
     *
     * @return an instance of {@link PlayersPersonalDataFull} that contains all requested player data. If the request fails, an empty instance is returned.
     * @throws IllegalArgumentException <ul>
     *                                  <li>If this method is called and region is null.</li>
     *                                  <li>If this method is called and accountIds is empty.</li>
     *                                  </ul>
     */
    @Override
    public PlayersPersonalDataFull fetch() {
        if (region == null || accountIds.size() == 0) {
            throw new IllegalArgumentException("The region has to be set and accountIds must not be empty");
        }
        String path = "/wows/account/info/";
        String accounts = accountIds.stream().sequential().map(Object::toString).collect(Collectors.joining(","));
        PlayersPersonalDataFull result;
        String url = baseUrl(region, path, language) + FieldType.ACCOUNT_ID + accounts + buildFieldString(FieldType.EXTRA, extraFields);
//        SimpleRateLimiter.waitForPermit();
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
//            result = GSON.fromJson(reader, PlayersPersonalDataFull.class);
//        } catch (IOException e) {
//            LOG.error("An IOException occurred", e);
//            result = new PlayersPersonalDataFull();
//        } catch (Exception e2) {
//            LOG.error("Unexpected exception detected.", e2);
//            throw new IllegalStateException("Error while processing request");
//        }
        return connect(url, PlayersPersonalDataFull.class);
    }

    /**
     * An enum that represents all available extra fields for the api request.
     * <p>By default the api response only contains data for the {@link #PVP} field.
     * If you want to get data for other modes, you have to add one of the fields to the request.</p>
     *
     * <p>Currently, the default pvp field cannot be removed from the api response.</p>
     */
    @AllArgsConstructor
    public enum ExtraField implements IResponseFields {
        /**
         * The default random battle mode, including divison games.
         * Always a part of the api response unless it's manually disabled.
         */
        PVP("statistics.pvp"), // as standard in each stats related request, unless you exclude it via "&fields=-statistics.pvp"
        /**
         * Only solo games in the random battles mode.
         */
        PVP_SOLO("statistics.pvp_solo"),
        /**
         * Only division games with divisions of 2 players in the random battles mode.
         */
        PVP_DIV2("statistics.pvp_div2"),
        /**
         * Only division games with divisions of 3 players in the random battles mode.
         */
        PVP_DIV3("statistics.pvp_div3"),
        /**
         * The coop battles mode. Contains solo and division statistics.
         */
        PVE("statistics.pve"),
        /**
         * Only solo games in the coop battles mode.
         */
        PVE_SOLO("statistics.pve_solo"),
        /**
         * Only division games with 2 players in the coop battles mode.
         */
        PVE_DIV2("statistics.pve_div2"),
        /**
         * Only division games with 3 players in the coop battles mode.
         */
        PVE_DIV3("statistics.pve_div3"),
        /**
         * Only solo games in ranked battles mode
         */
        RANK_SOLO("statistics.rank_solo"),
        /**
         * Only division games with 2 players in the ranked battles mode.
         */
        RANK_DIV2("statistics.rank_div2"),
        /**
         * Only division games with 3 players in the ranked battles mode.
         */
        RANK_DIV3("statistics.rank_div3"),
        /**
         * Only solo games in the operations mode.
         */
        OPER_SOLO("statistics.oper_solo"),
        /**
         * Only division games in the operations mode.
         */
        OPER_DIV("statistics.oper_div"),
        /**
         * Only division games in the operations mode with difficulty level hard.
         */
        OPER_DIV_HARD("statistics.oper_div_hard");

        /**
         * The field name for the api.
         */
        private String key;

        /**
         * A method to get the key of an entry.
         *
         * @return the key associated with the entry
         */
        @Override
        public String retrieveKey() {
            return key;
        }
    }


}
