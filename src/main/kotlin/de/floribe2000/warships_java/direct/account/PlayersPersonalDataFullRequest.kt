package de.floribe2000.warships_java.direct.account

import de.floribe2000.warships_java.direct.api.AbstractRequest
import de.floribe2000.warships_java.direct.api.IResponseFields
import de.floribe2000.warships_java.direct.api.connect
import de.floribe2000.warships_java.direct.api.typeDefinitions.FieldType
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import org.slf4j.LoggerFactory
import kotlin.collections.HashSet

/**
 * A class to create and execute requests to retrieve personal data for one or more players from the api.
 *
 * By default only pvp statistics and basic player details are retrieved, more fields can be specified by using
 * the [.extraFields] or [.addExtraField] methods.
 *
 *
 * The request is executed by calling the [.fetch] method, for details see the javadoc of those methods.
 *
 * @author floribe2000
 */
class PlayersPersonalDataFullRequest : AbstractRequest<PlayersPersonalDataFullRequest, PlayersPersonalDataFull>() {
    /**
     * A Logger instance used to log events of this class
     */
    private val log = LoggerFactory.getLogger(javaClass.simpleName)

    /**
     * The region for the request. Must not be null when fetch ist called.
     */
    private lateinit var selectedRegion: Region

    /**
     * The language for the api response
     */
    private var language: Language? = null

    /**
     * A set of accound ids for the request.
     */
    private var accountIds: MutableSet<Long> = HashSet()

    /**
     * A set of extra fields that should be added to the request and will be retrieved from the api.
     */
    private var extraFields: MutableSet<ExtraField> = HashSet()

    override fun region(region: Region): PlayersPersonalDataFullRequest {
        this.selectedRegion = region
        return this
    }

    override fun language(language: Language): PlayersPersonalDataFullRequest {
        this.language = language
        return this
    }

    override fun buildUrl(): String {
        require(this::selectedRegion.isInitialized && accountIds.size > 0) {
            "The region has to be set and accountIds must not be empty"
        }
        val path = "/wows/account/info/"
        val accounts = accountIds.joinToString(",")
        return baseUrl(selectedRegion, path, language, instanceName) + FieldType.ACCOUNT_ID + accounts + buildFieldString(FieldType.EXTRA, extraFields)
    }

    /**
     * Adds one or more fields to the [.extraFields] set.
     *
     * Existing fields won't be changed!
     *
     * @param fields the fields to add to the request's extra fields
     * @return the instance of this request
     */
    fun addExtraField(vararg fields: ExtraField): PlayersPersonalDataFullRequest {
        extraFields.addAll(fields.asList())
        return this
    }

    /**
     * Adds an account id to the list of account ids.
     *
     * Existing ids won't be changed!
     * If the limit is reached, the id won't be added to the request and a logging call is triggered.
     *
     * @param accountId the id to add
     * @return the instance of this request
     */
    fun addAccountId(accountId: Long): PlayersPersonalDataFullRequest {
        if (accountIds.size < 100) {
            accountIds.add(accountId)
        } else {
            log.warn("Skipping account id addition. Reason: Limit reached (Limit: 100)")
        }
        return this
    }

    /**
     * Sets the list of account ids.
     *
     * Warning: the existing ids are replaced!
     *
     * @param accountIds the ids that should be added
     * @return the instance of this request
     * @throws IllegalArgumentException If the size of the set exceeds the limit of 100 ids
     */
    fun accountIds(accountIds: Collection<Long>): PlayersPersonalDataFullRequest {
        val tmp: MutableSet<Long> = HashSet(accountIds)
        require(tmp.size <= 100) { "The size of the set must not exceed 100" }
        this.accountIds = tmp
        return this
    }

    /**
     * Adds one or more [extra fields][ExtraField] to the request.
     *
     * Existing fields won't be changed.
     *
     * @param extraFields the fields to add to the request
     * @return the instance of this request
     * @see .extraFields
     */
    fun extraFields(vararg extraFields: ExtraField): PlayersPersonalDataFullRequest {
        return extraFields(extraFields.asList())
    }

    /**
     * Adds one or more [extra fields][ExtraField] to the request.
     *
     * Existing fields won't be changed.
     *
     * @param extraFields the fields to add to the request
     * @return the instance of this request
     */
    fun extraFields(extraFields: Collection<ExtraField>): PlayersPersonalDataFullRequest {
        this.extraFields.addAll(extraFields)
        return this
    }

    /**
     * Executes a request and returns the result of the request.
     *
     * All requests are executed synchronous on this thread. It is safe to execute this in a new thread if it is required to be run asynchronous.
     *
     * @return an instance of [PlayersPersonalDataFull] that contains all requested player data. If the request fails, an empty instance is returned.
     * @throws IllegalArgumentException
     *  * If this method is called and region is null.
     *  * If this method is called and accountIds is empty.
     *
     */
    override fun fetch(url: String): PlayersPersonalDataFull {
        return connect(url, limiter)
    }

    override fun apiBuilder(instanceName: String): PlayersPersonalDataFullRequest {
        setInstance(instanceName)
        return this
    }

    /**
     * An enum that represents all available extra fields for the api request.
     *
     * By default the api response only contains data for the [.PVP] field.
     * If you want to get data for other modes, you have to add one of the fields to the request.
     *
     *
     * Currently, the default pvp field cannot be removed from the api response.
     */
    enum class ExtraField(
            /**
             * The field name for the api.
             */
            override val key: String) : IResponseFields {
        /**
         * The default random battle mode, including divison games.
         * Always a part of the api response unless it's manually disabled.
         */
        PVP("statistics.pvp"),  // as standard in each stats related request, unless you exclude it via "&fields=-statistics.pvp"

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
    }

    companion object {
        /**
         * Creates a new empty request instance of this class.
         *
         * @return a new instance of this class
         */
        fun createRequest(): PlayersPersonalDataFullRequest {
            return PlayersPersonalDataFullRequest()
        }
    }
}