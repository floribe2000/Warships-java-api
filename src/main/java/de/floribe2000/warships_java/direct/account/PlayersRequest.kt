package de.floribe2000.warships_java.direct.account

import de.floribe2000.warships_java.direct.api.AbstractRequest
import de.floribe2000.warships_java.direct.api.IResponseFields
import de.floribe2000.warships_java.direct.api.typeDefinitions.FieldType
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import org.slf4j.LoggerFactory

/**
 * A class to create and execute requests to retrieve a list of players based on a search string.
 *
 * Up to 100 players can be retrieved, if there is an exact match only one player will be returned.
 *
 *
 * For details about this request see [WG api reference](https://developers.wargaming.net/reference/all/wows/account/list/).
 *
 * @author floribe2000
 */
class PlayersRequest : AbstractRequest<PlayersRequest, Players>() {
    /**
     * A Logger instance used to log events of this class
     */
    private val LOG = LoggerFactory.getLogger(this::class.simpleName)

    /**
     * The server region for this request
     */
    private var region: Region? = null

    /**
     * The language for the api response
     */
    private var language: Language? = null

    /**
     * The search text for this request
     */
    private var searchText: String? = null

    /**
     * The response fields for this request
     */
    private var fields: MutableSet<ResponseField> = HashSet()

    override fun region(region: Region): PlayersRequest {
        this.region = region
        return this
    }

    override fun language(language: Language): PlayersRequest {
        this.language = language
        return this
    }

    override fun buildUrl(): String {
        require(!(region == null || searchText == null)) { "You can't use this method before setting all parameters" }
        val path = "/wows/account/list/"
        return baseUrl(region!!, path, language, instanceName) + "&search=" + searchText + buildFieldString(FieldType.FIELDS, fields)
    }

    override fun apiBuilder(instanceName: String): PlayersRequest {
        setInstance(instanceName)
        return this
    }

    /**
     * Sets the search text for the request.
     *
     * Can be called multiple times. Important: The existing search text is replaced by this method!
     *
     * @param text the new search text
     * @return the instance of this request
     */
    fun searchText(text: String?): PlayersRequest {
        searchText = text
        return this
    }

    /**
     * Adds a field to the request while keeping all existing fields.
     *
     * @param fields the fields to add
     * @return this instance
     */
    fun addFields(vararg fields: ResponseField): PlayersRequest {
        this.fields.addAll(fields.asList())
        return this
    }

    /**
     * Replaces all currently set fields with a new list of fields.
     *
     * @param fields the new fields
     * @return this instance
     * @see .fields
     */
    fun fields(vararg fields: ResponseField): PlayersRequest {
        return fields(fields.asList())
    }

    /**
     * Replaces all currently set fields with a new list of fields.
     *
     * @param fields the new fields
     * @return this instance
     */
    fun fields(fields: Collection<ResponseField>): PlayersRequest {
        this.fields = HashSet(fields)
        return this
    }

    /**
     * Executes a request and returns the result of the request.
     *
     * All requests are executed synchronous on this thread. Don't use the same request in multiple threads. Use [.fetchAsync] instead.
     *
     * @return an instance of [Players] that contains all requested player data. If the request fails, an empty instance is returned.
     * @throws IllegalArgumentException
     *  * If this method is called and region is null.
     *  * If this method is called and searchText is null.
     *
     */
    override fun fetch(url: String): Players {
        return connect(url, Players::class.java, limiter)!!
    }

    /**
     * All response fields of the api response.
     *
     * By default, both fields are shown. Only use them if you need only one of them.
     */
    enum class ResponseField(private val key: String) : IResponseFields {
        ACCOUNT_ID("account_id"),
        NICKNAME("nickname");

        override fun retrieveKey(): String {
            return key
        }
    }

    companion object {
        /**
         * Creates a new, empty request
         *
         * @return an instance of this class
         */
        fun createRequest(): PlayersRequest {
            return PlayersRequest()
        }
    }
}