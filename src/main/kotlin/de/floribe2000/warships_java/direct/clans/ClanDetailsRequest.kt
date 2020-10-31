package de.floribe2000.warships_java.direct.clans

import de.floribe2000.warships_java.direct.api.AbstractRequest
import de.floribe2000.warships_java.direct.api.IResponseFields
import de.floribe2000.warships_java.direct.api.typeDefinitions.FieldType
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import org.slf4j.LoggerFactory

/**
 * A class to create and execute requests to retrieve details of selected clans
 *
 * Up to 100 clans can be retrieved in one request. For more clans you have to use this request multiple times.
 *
 * It is possible to just replace the list of clan ids before calling [.fetch] so you don't have to create a new request instance every time.
 *
 * @author floribe2000
 */
class ClanDetailsRequest private constructor() : AbstractRequest<ClanDetailsRequest, ClanDetails>() {
    /**
     * A Logger instance used to log events of this class
     */
    private val log = LoggerFactory.getLogger(javaClass.simpleName)

    /**
     * The server region for this request
     */
    private lateinit var selectedRegion: Region

    /**
     * The language for the api response
     */
    private var language: Language? = null

    /**
     * The set of clan ids for this request
     */
    private var clanIds: MutableSet<Long> = HashSet()

    /**
     * The extra field for this request
     */
    private var extra = false

    override fun region(region: Region): ClanDetailsRequest {
        this.selectedRegion = region
        return this
    }

    override fun language(language: Language): ClanDetailsRequest {
        this.language = language
        return this
    }

    override fun apiBuilder(instanceName: String): ClanDetailsRequest {
        setInstance(instanceName)
        return this
    }

    /**
     * Adds an extra field to the request.
     *
     * Since there is only one extra field available for this request, this method can also be used to toggle this field.
     * To remove it, simply call the method with null as parameter. To add it, call the method with [ExtraField.MEMBERS] as parameter.
     *
     * @param field the field to add
     * @return the instance of this request
     */
    fun extra(field: ExtraField?): ClanDetailsRequest {
        extra = field != null //Since there is only one field, there is no need to store that field
        return this
    }

    /**
     * Adds a collection of clan ids to this request.
     *
     * Replaces existing entries!
     *
     * @param clanIds the collection of clan ids to add
     * @return the instance of this request
     * @throws IllegalArgumentException
     *  * If the collection of clan ids is null or empty.
     *  * If the collection of clan ids contains more than 100 entries.
     *
     */
    fun clanIds(clanIds: Collection<Long>): ClanDetailsRequest {
        require(clanIds.isNotEmpty() && clanIds.size <= 100) { "The collection must not be null and have between 1 and 100 entries" }
        this.clanIds = HashSet(clanIds)
        return this
    }

    /**
     * Adds a single clan to the request.
     *
     * Doesn't change existing entries. If the list is full, nothing will happen.
     *
     * @param clanId the clan id to add
     * @return the instance of this request
     */
    fun addClan(clanId: Long): ClanDetailsRequest {
        if (clanIds.size < 100) {
            clanIds.add(clanId)
        }
        return this
    }

    /**
     * Executes a request and returns the result of the request.
     *
     * All requests are executed synchronous on this thread. It is safe to execute this in a new thread if it is required to be run asynchronous.
     *
     * @return an instance of [ClanDetails] that contains all requested player data. If the request fails, an empty instance is returned.
     * @throws IllegalArgumentException
     *  * If this method is called and region is null.
     *  * If this method is called and the list of clan ids is empty.
     *  * If this method is called and the list of clan ids contains more than 100 entries.
     *
     */
    override fun fetch(url: String): ClanDetails {
        return connect(url, ClanDetails::class.java, limiter)
    }

    override fun buildUrl(): String {
        require(this::selectedRegion.isInitialized && clanIds.size in 1..100) {
            "Region has to be initialized and the number of clans must be between 1 and 100."
        }
        val path = "/wows/clans/info/"
        val clans = clanIds.joinToString(",")
        val extra = if (!extra) "" else FieldType.EXTRA.toString() + ExtraField.MEMBERS.key
        return baseUrl(selectedRegion, path, language, instanceName) + FieldType.CLAN + clans + extra
    }

    /**
     * The extra fields for this request.
     *
     * In this case, there is only a single field available.
     */
    enum class ExtraField(override val key: String) : IResponseFields {
        /**
         * If selected includes a list of all clan members with their [ClanRole], join date and account id.
         */
        MEMBERS("members");

        override fun retrieveKey(): String {
            return key
        }
    }

    companion object {
        /**
         * Creates a new empty request of this class.
         *
         * @return an instance of this class
         */
        fun createRequest(): ClanDetailsRequest {
            return ClanDetailsRequest()
        }
    }
}