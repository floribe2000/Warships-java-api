package de.floribe2000.warships_java.direct.encyclopedia

import de.floribe2000.warships_java.direct.api.AbstractRequest
import de.floribe2000.warships_java.direct.api.connect
import de.floribe2000.warships_java.direct.api.typeDefinitions.ConsumableType
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region

/**
 * A class to request details about different consumables from the game.
 *
 * Not all consumables are supported at the moment because the api does not provide information for all of them.
 *
 * @author floribe2000
 * @since 0.2.12
 */
@Suppress("UNUSED")
class ConsumablesRequest private constructor() : AbstractRequest<ConsumablesRequest, Consumables>() {
    /**
     * The server region for this request
     */
    private lateinit var selectedRegion: Region

    /**
     * The language for the api response
     */
    private var language: Language? = null

    /**
     * The type of consumables for this request
     */
    private var type: ConsumableType? = null

    /**
     * The page for the request
     */
    private var pageNo = 1

    /**
     * A possibly empty list of consumable ids for the request
     */
    private var consumableId: MutableSet<Long> = HashSet()

    override fun apiBuilder(instanceName: String): ConsumablesRequest {
        setInstance(instanceName)
        return this
    }

    override fun region(region: Region): ConsumablesRequest {
        this.selectedRegion = region
        return this
    }

    override fun language(language: Language): ConsumablesRequest {
        this.language = language
        return this
    }

    /**
     * Defines the type of consumables for the request. As an optional field, this can be null.
     *
     * @param type the type of consumables for this request
     * @return the instance of this request
     */
    fun type(type: ConsumableType): ConsumablesRequest {
        this.type = type
        return this
    }

    /**
     * Defines the page of the request.
     *
     * @param pageNo the page of the request, has to be 1 or more
     * @return the instance of this request
     * @throws IllegalArgumentException If the provided page number is less than 1.
     */
    fun page(pageNo: Int): ConsumablesRequest {
        require(pageNo >= 1) { "Page number has to be 1 or more." }
        this.pageNo = pageNo
        return this
    }

    /**
     * Adds one or more consumable ids to the list of consumables to retrieve.
     *
     * The total amount of requested consumables must not exceed 100!
     *
     * Will replace existing values.
     *
     * @param consumable_id a collection of ids for this request
     * @return the instance of this request
     * @throws IllegalArgumentException If the size of the consumable_id collection exceeds the limit of 100.
     */
    fun consumableId(consumable_id: Collection<Long>): ConsumablesRequest {
        val tmp: MutableSet<Long> = HashSet(consumable_id)
        require(tmp.size <= 100) { "Not more than 100 ids must be specified!" }
        this.consumableId = tmp
        return this
    }

    /**
     * Adds one or more consumable ids to the list of consumables to retrieve.
     *
     * The total amount of requested consumables must not exceed 100!
     *
     * Does not replace existing values.
     *
     * @param consumableId the consumable ids to add
     * @return the instance of this request
     * @throws IllegalArgumentException If the amount of consumable ids would exceed 100 after adding the new elements.
     */
    fun addConsumable(vararg consumableId: Long): ConsumablesRequest {
        var newItems = 0
        for (l in consumableId) {
            if (!this.consumableId.contains(l)) {
                newItems++
            }
        }
        require(newItems + this.consumableId.size <= 100) { "Not more than 100 ids must be specified!" }
        this.consumableId.addAll(consumableId.asList())
        return this
    }

    /**
     * Executes a request and returns the result of the request.
     *
     * All requests are executed synchronous on this thread. It is safe to execute this in a new thread if it is required to be run asynchronous.
     *
     * @return an instance of [Consumables] that contains all requested consumable data. If the request fails, an empty instance is returned.
     * @throws IllegalArgumentException If this method is called and region is null.
     */
    override fun fetch(url: String): Consumables {
        return connect(url, limiter)
    }

    override fun buildUrl(): String {
        require(this::selectedRegion.isInitialized) { "The region has to be initialized." }
        val path = "/wows/encyclopedia/consumables/"
        val type = if (type != null) type.toString() else ""
        val page = if (pageNo > 1) "&page_no=$pageNo" else ""
        return baseUrl(selectedRegion, path, language, instanceName) + type + page
    }

    companion object {
        /**
         * Creates a new empty request of this class.
         *
         * @return an instance of this class
         */
        fun createRequest(): ConsumablesRequest {
            return ConsumablesRequest()
        }
    }
}