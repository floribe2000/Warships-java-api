package de.floribe2000.warships_java.direct.encyclopedia

import de.floribe2000.warships_java.direct.api.AbstractRequest
import de.floribe2000.warships_java.direct.api.typeDefinitions.*
import org.slf4j.LoggerFactory

/**
 * A class to create requests to get information about ships.
 *
 * Up to 100 ships can be retrieved per request, depending on the defined parameters less entries can be returned.
 *
 * Details about the total amount of hits and the total number of pages can be retrieved by analyzing the
 * [Meta][de.floribe2000.warships_java.direct.api.Meta] object of the api response
 * returned by [fetch].
 *
 * @author floribe2000
 */
class WarshipsRequest : AbstractRequest<WarshipsRequest, Warships>() {
    /**
     * A Logger instance used to log events of this class
     */
    private val LOG = LoggerFactory.getLogger(javaClass.simpleName)

    /**
     * The server region for this request
     */
    private lateinit var selectedRegion: Region

    /**
     * The language for the api response
     */
    private var language: Language? = null

    /**
     * A set of ship Ids for the request. May be empty, maximum size is 100
     */
    private var shipIds: MutableSet<Long> = HashSet()

    /**
     * The page of the response
     */
    private var pageNo = 1

    /**
     * The limit of entries per page of the response. Maximum value is 100.
     */
    private var limit = 100

    /**
     * A set of nations for the request
     */
    private val nations: MutableSet<Nation> = HashSet()

    /**
     * A set of ship types for the request
     */
    private val shipTypes: MutableSet<ShipType> = HashSet()

    /**
     * A set of ship categories for the request
     */
    private val shipCategories: MutableSet<ShipCategory> = HashSet()

    /**
     * A set of ship tiers for the request
     */
    private val shipTiers: MutableSet<Tier> = HashSet()
    override fun apiBuilder(instanceName: String): WarshipsRequest {
        setInstance(instanceName)
        return this
    }

    override fun region(region: Region): WarshipsRequest {
        this.selectedRegion = region
        return this
    }

    override fun language(language: Language): WarshipsRequest {
        this.language = language
        return this
    }

    /**
     * Defines the page number of the response. Valid values depend on the amount of results for a request
     *
     * @param pageNo the number of the page
     * @return the instance of this request
     */
    fun pageNo(pageNo: Int): WarshipsRequest {
        this.pageNo = pageNo
        return this
    }

    /**
     * Sets the limit of results per page for the request.
     *
     * @param limit the new limit, must be within 1 and 100
     * @return the instance of this request
     */
    fun limit(limit: Int): WarshipsRequest {
        return if (limit in 1..100) {
            this.limit = limit
            this
        } else {
            throw IllegalArgumentException("Limit must be within 1 and 100.")
        }
    }

    /**
     * Sets the ship ids for the request. A maximum of 100 ship ids may be set.
     *
     * Replaces existing values!
     *
     * @param shipIds the collection of ship ids for the request
     * @return the instance of this request
     */
    fun shipIds(shipIds: Collection<Long>): WarshipsRequest {
        return if (shipIds.size in 1..100) {
            this.shipIds = HashSet(shipIds)
            this
        } else {
            throw IllegalArgumentException("The size of the provided collection must be within 1 and 100.")
        }
    }

    /**
     * Adds one or more ship ids to the request. If the total number of ship ids after adding the new ones would be
     * bigger than 100, an exception is thrown.
     *
     * Doesn't change existing values.
     *
     * @param shipId the ship ids to add
     * @return the instance of this request
     * @throws IllegalArgumentException If the total number of ship ids after adding the new ones is bigger than 100.
     */
    fun addShipId(vararg shipId: Long): WarshipsRequest {
        val newEntries = shipId.filter { id: Long -> !shipIds.contains(id) }.toSet()
        return if (shipIds.size + newEntries.size <= 100) {
            shipIds.addAll(newEntries)
            this
        } else {
            throw IllegalArgumentException("You cannot specify more than 100 ship ids.")
        }
    }

    /**
     * Adds one or more nations to the request.
     *
     * Does NOT replace existing values!
     *
     * @param nations the nations to add
     * @return the instance of this request
     */
    fun nation(vararg nations: Nation): WarshipsRequest {
        this.nations.addAll(nations.asList())
        return this
    }

    /**
     * Adds one or more nations to the request.
     *
     * Does NOT replace existing values!
     *
     * @param nations the nations to add
     * @return the instance of this request
     */
    fun nation(nations: Collection<Nation>): WarshipsRequest {
        this.nations.addAll(nations)
        return this
    }

    /**
     * Adds one or more ship types to the request.
     *
     * Does NOT replace existing values!
     *
     * @param types the ship types to add
     * @return the instance of this request
     */
    fun shipType(vararg types: ShipType): WarshipsRequest {
        shipTypes.addAll(types.asList())
        return this
    }

    /**
     * Adds one or more ship types to the request.
     *
     * Does NOT replace existing values!
     *
     * @param types the ship types to add
     * @return the instance of this request
     */
    fun shipType(types: Collection<ShipType>): WarshipsRequest {
        shipTypes.addAll(types)
        return this
    }

    /**
     * Adds one or more ship tiers to the request.
     *
     * Does NOT replace existing values!
     *
     * @param tiers the ship tiers to add
     * @return the instance of this request
     */
    fun tier(vararg tiers: Tier): WarshipsRequest {
        shipTiers.addAll(tiers.asList())
        return this
    }

    /**
     * Adds one or more ship tiers to the request.
     *
     * Does NOT replace existing values!
     *
     * @param tiers the ship tiers to add
     * @return the instance of this request
     */
    fun tier(tiers: Collection<Tier>): WarshipsRequest {
        shipTiers.addAll(tiers)
        return this
    }

    /**
     * Adds one or more ship categories to the request.
     *
     * Does NOT replace existing values!
     *
     * @param categories the ship categories to add
     * @return the instance of this request
     */
    fun shipCategory(vararg categories: ShipCategory): WarshipsRequest {
        shipCategories.addAll(categories.asList())
        return this
    }

    /**
     * Adds one or more ship categories to the request.
     *
     * Does NOT replace existing values!
     *
     * @param categories the ship categories to add
     * @return the instance of this request
     */
    fun shipCategory(categories: Collection<ShipCategory>): WarshipsRequest {
        shipCategories.addAll(categories)
        return this
    }

    /**
     * Executes a request and returns the result of the request.
     *
     * All requests are executed synchronous on this thread. It is safe to execute this in a new thread if it is required to be run asynchronous.
     *
     * @return an instance of [Warships] that contains all requested player data. If the request fails, an empty instance is returned.
     * @throws IllegalArgumentException If this method is called and region is null.
     */
    override fun fetch(url: String): Warships {
        val response = connect(url, Warships::class.java, limiter)

        if (shipTiers.isNotEmpty()) {
            val tmpMap = response.data?.filter { entry -> entry.value.tier in shipTiers } ?: mutableMapOf()
            response.data?.clear()
            response.data?.putAll(tmpMap)
        }

        if (shipCategories.isNotEmpty()) {
            val tmpMap = response.data?.filter { entry ->
                (ShipCategory.PREMIUM in shipCategories && entry.value.isPremium) ||
                        (ShipCategory.SPECIAL in shipCategories && entry.value.isSpecial) ||
                        (ShipCategory.RESEARCH in shipCategories && !entry.value.isPremium && !entry.value.isSpecial)
            } ?: mutableMapOf()
            response.data?.clear()
            response.data?.putAll(tmpMap)
        }

        response.data?.values?.forEach {
            it.name = formattingFixer(it.name ?: "Undefined")
        }

        return response
    }

    /**
     * Utility method to convert non-blocking spaces (ASCII Dec 160) to regular spaces (ASCII Dec 30).
     *
     * Some ships have those spaces in their names which prevents finding them when using the [String.contains] method.
     *
     * @param oldValue the old string containing the non-blocking spaces
     * @return the new value containing only regular spaces
     */
    private fun formattingFixer(oldValue: String): String {
        return oldValue.replace(160.toChar(), ' ')
    }

    override fun buildUrl(): String {
        require(this::selectedRegion.isInitialized) { "Region must not be null." }
        val path = "/wows/encyclopedia/ships/"
        val nations = if (nations.isEmpty()) "" else FieldType.NATION.toString() + nations.joinToString(",") { obj: Nation -> obj.toString() }
        val types = if (shipTypes.isEmpty()) "" else FieldType.SHIP_CLASS.toString() + shipTypes.joinToString(",") { obj: ShipType -> obj.toString() }
        val ships = if (shipIds.isEmpty()) "" else FieldType.SHIP_ID.toString() + shipIds.joinToString(",") { o: Long -> o.toString() }
        return baseUrl(selectedRegion, path, language, instanceName) + ships + nations + types + FieldType.PAGE + pageNo + FieldType.LIMIT + limit
    }

    companion object {
        /**
         * Creates a new empty request of this class.
         *
         * @return an instance of this class
         */
        fun createRequest(): WarshipsRequest {
            return WarshipsRequest()
        }
    }
}