package de.floribe2000.warships_java.direct.warships

import de.floribe2000.warships_java.direct.api.AbstractRequest
import de.floribe2000.warships_java.direct.api.IResponseFields
import de.floribe2000.warships_java.direct.api.typeDefinitions.*
import de.floribe2000.warships_java.direct.encyclopedia.Warships
import de.floribe2000.warships_java.direct.encyclopedia.WarshipsRequest
import org.slf4j.LoggerFactory

/**
 * A class to create and execute requests to get details about the stats of single ships for a player.
 *
 * If the list of ships is not defined, statistics for all ships will be returned. If the list of ships is defined, statistics for up to 100 ships can be requested.
 *
 * @author floribe2000, SirLefti
 */
//TODO allow use of all parameters
class StatisticsRequest : AbstractRequest<StatisticsRequest, Statistics>() {
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
     * The account id of the player for this request
     */
    private var accountId: Long = 0

    /**
     * The extra fields for this request
     */
    private var extraFields: MutableSet<ExtraField> = HashSet()

    /**
     * A set of up to 100 ship ids for this request
     */
    private var shipIds: MutableSet<Long> = HashSet()

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
    private var shipCategories: MutableSet<ShipCategory> = HashSet()

    /**
     * A set of ship tiers for the request
     */
    private var shipTiers: MutableSet<Tier> = HashSet()

    override fun region(region: Region): StatisticsRequest {
        this.selectedRegion = region
        return this
    }

    override fun language(language: Language): StatisticsRequest {
        this.language = language
        return this
    }

    override fun apiBuilder(instanceName: String): StatisticsRequest {
        setInstance(instanceName)
        return this
    }

    /**
     * Sets the account id for this request.
     *
     * Replaces existing values!
     *
     * @param accountId the new account id for this request
     * @return the instance of this request
     */
    fun accountId(accountId: Long): StatisticsRequest {
        this.accountId = accountId
        return this
    }

    /**
     * Sets the set of extra fields.
     *
     * Replaces existing values! To add fields use [.addExtraField].
     *
     * @param extraFields the collection of [ExtraFields][ExtraField] to set
     * @return the instance of this request
     */
    fun extraFields(extraFields: Collection<ExtraField>): StatisticsRequest {
        this.extraFields = HashSet(extraFields)
        return this
    }

    /**
     * Sets the set of extra fields.
     *
     * Replaces existing values! To add fields use [.addExtraField].
     *
     * @param extraFields the array of [ExtraFields][ExtraField] to set
     * @return the instance of this request
     */
    fun extraFields(vararg extraFields: ExtraField): StatisticsRequest {
        this.extraFields = HashSet(extraFields.asList())
        return this
    }

    /**
     * Adds an extra field to this request.
     *
     * Does not change existing values!
     *
     * @param extraField the [ExtraField] to add
     * @return the instance of this request
     */
    fun addExtraField(extraField: ExtraField): StatisticsRequest {
        extraFields.add(extraField)
        return this
    }

    /**
     * Add extra fields to this request.
     *
     * Does not change existing values!
     *
     * @param extraFields the [ExtraFields][ExtraField] to add
     * @return the instance of this request
     */
    fun addExtraField(extraFields: Collection<ExtraField>): StatisticsRequest {
        this.extraFields.addAll(extraFields)
        return this
    }

    /**
     * Add extra fields to this request.
     *
     * Does not change existing values!
     *
     * @param extraFields the [ExtraFields][ExtraField] to add
     * @return the instance of this request
     */
    fun addExtraField(vararg extraFields: ExtraField): StatisticsRequest {
        this.extraFields.addAll(extraFields.asList())
        return this
    }

    /**
     * Removes the given extra field from this request.
     *
     * @param extraField the [ExtraField] to remove
     * @return the instance of this request
     */
    fun removeExtraField(extraField: ExtraField): StatisticsRequest {
        extraFields.remove(extraField)
        return this
    }

    /**
     * Removes the given extra fields from this request.
     *
     * @param extraFields the [ExtraFields][ExtraField] to remove
     * @return the instance of this request
     */
    fun removeExtraFields(extraFields: Collection<ExtraField>): StatisticsRequest {
        this.extraFields.removeAll(extraFields)
        return this
    }

    /**
     * Removes the given extra fields from this request.
     *
     * @param extraFields the [ExtraFields][ExtraField] to remove
     * @return the instance of this request
     */
    fun removeExtraFields(vararg extraFields: ExtraField): StatisticsRequest {
        this.extraFields.removeAll(extraFields.asList())
        return this
    }

    /**
     * Sets the set of ship ids for this request.
     *
     * Replaces existing values! To add ships use [.addShipIds] instead.
     *
     * @param shipIds the collection of [shipIds][Long] to set
     * @return the instance of this request
     * @throws IllegalStateException If the collection contains more than 100 ids.
     */
    fun shipIds(shipIds: Collection<Long>): StatisticsRequest {
        if (shipIds.size <= 100) {
            this.shipIds = HashSet(shipIds)
        } else {
            throw IllegalStateException("There must be not more than 100 ship ids.")
        }
        return this
    }

    /**
     * Sets the set of ship ids for this request.
     *
     * Replaces existing values! To add ships use [.addShipIds] instead.
     *
     * @param shipIds the array of [shipIds][Long] to set
     * @return the instance of this request
     * @throws IllegalStateException If the array contains more than 100 ids.
     */
    fun shipIds(vararg shipIds: Long): StatisticsRequest {
        if (shipIds.size <= 100) {
            this.shipIds = HashSet(shipIds.asList())
        } else {
            throw IllegalStateException("There must be not more than 100 ship ids.")
        }
        return this
    }

    /**
     * Adds a ship id to the request.
     *
     * Does not change existing values!
     *
     * @param shipId the [shipId][Long] to add
     * @return the instance of this request
     * @throws IllegalStateException If there are already 100 ids set for this request.
     */
    fun addShipId(shipId: Long): StatisticsRequest {
        if (shipIds.size < 100) {
            shipIds.add(shipId)
        } else {
            throw IllegalStateException("There must be not more than 100 ship ids.")
        }
        return this
    }

    /**
     * Adds given ship ids to the request.
     *
     * Does not change existing values!
     *
     * @param shipIds the collection of [shipIds][Long] to add
     * @return the instance of this request
     * @throws IllegalStateException If there would be set more than 100 ids for this request.
     */
    fun addShipIds(shipIds: Collection<Long>): StatisticsRequest {
        if (this.shipIds.size + shipIds.size <= 100) {
            this.shipIds.addAll(shipIds)
        } else {
            throw IllegalStateException("There must be not more than 100 ship ids.")
        }
        return this
    }

    /**
     * Adds given ship ids to the request.
     *
     * Does not change existing values!
     *
     * @param shipIds the array [shipIds][Long] to add
     * @return the instance of this request
     * @throws IllegalStateException If there would be set more than 100 ids for this request.
     */
    fun addShipIds(vararg shipIds: Long): StatisticsRequest {
        if (this.shipIds.size + shipIds.size <= 100) {
            this.shipIds.addAll(shipIds.asList())
        } else {
            throw IllegalStateException("There must be not more than 100 ship ids.")
        }
        return this
    }

    /**
     * Removes a ship id from the list of ids.
     *
     * @param shipId the [shipId][Long] to remove
     * @return the instance of this request
     */
    fun removeShipId(shipId: Long): StatisticsRequest {
        shipIds.remove(shipId)
        return this
    }

    /**
     * Removes given ship ids from the list of ids.
     *
     * @param shipIds the collection of [shipIds][Long] to remove
     * @return the instance of this request
     */
    fun removeShipIds(shipIds: Collection<Long>): StatisticsRequest {
        this.shipIds.removeAll(shipIds)
        return this
    }

    /**
     * Removes given ship ids from the list of ids.
     *
     * @param shipIds the array of [shipIds][Long] to remove
     * @return the instance of this request
     */
    fun removeShipIds(vararg shipIds: Long): StatisticsRequest {
        this.shipIds.removeAll(shipIds.asList())
        return this
    }

    /**
     * Sets the nations to the request.
     *
     * Replaces existing values! To add nations use [.addNations] instead.
     *
     * @param nations the collection of [nations][Nation] to set
     * @return the instance of this request
     */
    fun nations(nations: Collection<Nation>?): StatisticsRequest {
        this.nations.addAll(nations!!)
        return this
    }

    /**
     * Sets the nations to the request.
     *
     * Replaces existing values! To add nations use [.addNations] instead.
     *
     * @param nations the array of [nations][Nation] to set
     * @return the instance of this request
     */
    fun nations(vararg nations: Nation): StatisticsRequest {
        this.nations.addAll(nations.asList())
        return this
    }

    /**
     * Adds one nation to the request.
     *
     * Does not change existing values!
     *
     * @param nation the [nation][Nation] to add
     * @return the instance of this request
     */
    fun addNation(nation: Nation): StatisticsRequest {
        nations.add(nation)
        return this
    }

    /**
     * Adds one or more nations to the request.
     *
     * Does not change existing values!
     *
     * @param nations the collection of [nations][Nation] to add
     * @return the instance of this request
     */
    fun addNations(nations: Collection<Nation>): StatisticsRequest {
        this.nations.addAll(nations)
        return this
    }

    /**
     * Adds one or more nations to the request.
     *
     * Does not change existing values!
     *
     * @param nations the array of [nations][Nation] to add
     * @return the instance of this request
     */
    fun addNations(vararg nations: Nation): StatisticsRequest {
        this.nations.addAll(nations.asList())
        return this
    }

    /**
     * Removes a nation from the set of nations.
     *
     * @param nation the [nation][Nation] to remove
     * @return the instance of this request
     */
    fun removeNation(nation: Nation): StatisticsRequest {
        nations.remove(nation)
        return this
    }

    /**
     * Removes given nations from the set of nations.
     *
     * @param nations the collection of [nations][Nation] to remove
     * @return the instance of this request
     */
    fun removeNations(nations: Collection<Nation>): StatisticsRequest {
        this.nations.removeAll(nations)
        return this
    }

    /**
     * Removes given nations from the set of nations.
     *
     * @param nations the array of [nations][Nation] to remove
     * @return the instance of this request
     */
    fun removeNations(vararg nations: Nation): StatisticsRequest {
        this.nations.removeAll(nations.asList())
        return this
    }

    /**
     * Adds given ship types to the request.
     *
     * Replaces existing values! To add ship types use [.addShipTypes] instead.
     *
     * @param types the collection of [types][ShipType] to set
     * @return the instance of this request
     */
    fun shipTypes(types: Collection<ShipType>): StatisticsRequest {
        shipTypes.addAll(types)
        return this
    }

    /**
     * Adds given ship types to the request.
     *
     * Replaces existing values! To add ship types use [.addShipTypes] instead.
     *
     * @param types the array of [types][ShipType] to set
     * @return the instance of this request
     */
    fun shipTypes(vararg types: ShipType): StatisticsRequest {
        shipTypes.addAll(types.asList())
        return this
    }

    /**
     * Adds one or more ship types to the request.
     *
     * Does not change existing values!
     *
     * @param types the colleection of [types][ShipType] to add
     * @return the instance of this request
     */
    fun addShipTypes(types: Collection<ShipType>): StatisticsRequest {
        shipTypes.addAll(types)
        return this
    }

    /**
     * Adds one or more ship types to the request.
     *
     * Does not change existing values!
     *
     * @param types the array of [types][ShipType] to add
     * @return the instance of this request
     */
    fun addShipTypes(vararg types: ShipType): StatisticsRequest {
        shipTypes.addAll(types.asList())
        return this
    }

    /**
     * Adds one ship type to the request.
     *
     * Does not change existing values!
     *
     * @param type the [type][ShipType] to add
     * @return the instance of this request
     */
    fun addShipType(type: ShipType): StatisticsRequest {
        shipTypes.add(type)
        return this
    }

    /**
     * Remove given ship types to the request.
     *
     * Does not change existing values!
     *
     * @param types the collection of [types][ShipType] to remove
     * @return the instance of this request
     */
    fun removeShipTypes(types: Collection<ShipType>): StatisticsRequest {
        shipTypes.removeAll(types)
        return this
    }

    /**
     * Remove given ship types to the request.
     *
     * Does not change existing values!
     *
     * @param types the array of [types][ShipType] to remove
     * @return the instance of this request
     */
    fun removeShipTypes(vararg types: ShipType): StatisticsRequest {
        shipTypes.removeAll(types.asList())
        return this
    }

    /**
     * Remove given ship type to the request.
     *
     * Does not change existing values!
     *
     * @param type the [type][ShipType] to remove
     * @return the instance of this request
     */
    fun removeShipType(type: ShipType): StatisticsRequest {
        shipTypes.remove(type)
        return this
    }

    /**
     * Adds one or more ship tiers to the request.
     *
     * Replaces existing values! To add ship tiers use [.addTiers] instead.
     *
     * @param tiers the collection of [tiers][Tier] to set
     * @return the instance of this request
     */
    fun tiers(tiers: Collection<Tier>): StatisticsRequest {
        shipTiers = HashSet(tiers)
        return this
    }

    /**
     * Adds one or more ship tiers to the request.
     *
     * Replaces existing values! To add ship tiers use [.addTiers] instead.
     *
     * @param tiers the array of [tiers][Tier] to set
     * @return the instance of this request
     */
    fun tiers(vararg tiers: Tier): StatisticsRequest {
        shipTiers = HashSet(tiers.asList())
        return this
    }

    /**
     * Adds one or more ship tiers to the request.
     *
     * Does not change existing values!
     *
     * @param tiers the collection of [tiers][Tier] to add
     * @return the instance of this request
     */
    fun addTiers(tiers: Collection<Tier>): StatisticsRequest {
        shipTiers.addAll(tiers)
        return this
    }

    /**
     * Adds one or more ship tiers to the request.
     *
     * Does not change existing values!
     *
     * @param tiers the array of [tiers][Tier] to add
     * @return the instance of this request
     */
    fun addTiers(vararg tiers: Tier): StatisticsRequest {
        shipTiers.addAll(tiers.asList())
        return this
    }

    /**
     * Adds one ship tiers to the request.
     *
     * Does not change existing values!
     *
     * @param tier the [tier][Tier] to add
     * @return the instance of this request
     */
    fun addTier(tier: Tier): StatisticsRequest {
        shipTiers.add(tier)
        return this
    }

    /**
     * Removes one or more ship tiers to the request.
     *
     * @param tiers the collection of [tiers][Tier] to remove
     * @return the instance of this request
     */
    fun removeTiers(tiers: Collection<Tier>): StatisticsRequest {
        shipTiers.removeAll(tiers)
        return this
    }

    /**
     * Removes one or more ship tiers to the request.
     *
     * @param tiers the array of [tiers][Tier] to remove
     * @return the instance of this request
     */
    fun removeTiers(vararg tiers: Tier): StatisticsRequest {
        shipTiers.removeAll(tiers.asList())
        return this
    }

    /**
     * Removes one ship tiers to the request.
     *
     * @param tier the [tier][Tier] to remove
     * @return the instance of this request
     */
    fun removeTier(tier: Tier): StatisticsRequest {
        shipTiers.remove(tier)
        return this
    }

    /**
     * Sets the ship categories to the request.
     *
     * Replaces existing values! To add ship categories use [.addCategories] instead.
     *
     * @param categories the collection of [categories][ShipCategory] to set
     * @return the instance of this request
     */
    fun categories(categories: Collection<ShipCategory>): StatisticsRequest {
        shipCategories = HashSet(categories)
        return this
    }

    /**
     * Sets the ship categories to the request.
     *
     * Replaces existing values! To add ship categories use [.addCategories] instead.
     *
     * @param categories the array of [categories][ShipCategory] to set
     * @return the instance of this request
     */
    fun categories(vararg categories: ShipCategory): StatisticsRequest {
        shipCategories = HashSet(categories.asList())
        return this
    }

    /**
     * Adds one or more ship categories to the request.
     *
     * Does not change existing values!
     *
     * @param categories the collection of [categories][ShipCategory] to add
     * @return the instance of this request
     */
    fun addCategories(categories: Collection<ShipCategory>): StatisticsRequest {
        shipCategories.addAll(categories)
        return this
    }

    /**
     * Adds one or more ship categories to the request.
     *
     * Does not change existing values!
     *
     * @param categories the array of [categories][ShipCategory] to add
     * @return the instance of this request
     */
    fun addCategories(vararg categories: ShipCategory): StatisticsRequest {
        shipCategories.addAll(categories.asList())
        return this
    }

    /**
     * Adds one ship category to the request.
     *
     * Does not change existing values!
     *
     * @param category the [category][ShipCategory] to add
     * @return the instance of this request
     */
    fun addCategory(category: ShipCategory): StatisticsRequest {
        shipCategories.add(category)
        return this
    }

    /**
     * Removes one or more ship categories to the request.
     *
     * @param categories the collection of [categories][ShipCategory] to remove
     * @return the instance of this request
     */
    fun removeCategories(categories: Collection<ShipCategory>): StatisticsRequest {
        shipCategories.removeAll(categories)
        return this
    }

    /**
     * Removes one or more ship categories to the request.
     *
     * Does not change existing values!
     *
     * @param categories the array of [categories][ShipCategory] to remove
     * @return the instance of this request
     */
    fun removeCategories(vararg categories: ShipCategory): StatisticsRequest {
        shipCategories.removeAll(categories.asList())
        return this
    }

    /**
     * Removes one ship category to the request.
     *
     * Does not change existing values!
     *
     * @param category the [category][ShipCategory] to remove
     * @return the instance of this request
     */
    fun removeCategory(category: ShipCategory): StatisticsRequest {
        shipCategories.remove(category)
        return this
    }

    private fun getShipIds(baseShipSet: Set<Long>, filterNation: Set<Nation>, filterShipType: Set<ShipType>, filterShipCategory: Set<ShipCategory>, filterTier: Set<Tier>): Set<Long> {
        val request = WarshipsRequest.createRequest()
                .region(selectedRegion)
                .nation(filterNation)
                .shipType(filterShipType)
                .shipCategory(filterShipCategory)
                .tier(filterTier)
        var response: Warships
        val ships: MutableSet<Long> = HashSet()
        var pageNo = 1
        do {
            response = request.pageNo(pageNo).fetch()
            ships.addAll(response.data?.values?.map { shipEntry -> shipEntry.shipId } ?: listOf())
            pageNo++
        } while (response.meta?.page_total ?: -1 >= pageNo)
        if (baseShipSet.isNotEmpty()) {
            ships.retainAll(baseShipSet)
        }
        return ships
    }

    /**
     * Executes a request and returns the result of the request.
     *
     * All requests are executed synchronous on this thread. It is safe to execute this in a new thread if it is required to be run asynchronous.
     *
     * @return an instance of [Statistics] that contains all requested player data. If the request fails, an empty instance is returned.
     * @throws IllegalArgumentException
     *  * If this method is called and region is null.
     *  * If this method is called and the list of ship ids contains more than 100 entries.
     *  * If the provided account id is not within the range of valid account ids.
     *
     */
    override fun fetch(url: String): Statistics {
        return connect(url, Statistics::class.java, limiter)
    }

    override fun buildUrl(): String {
        require(this::selectedRegion.isInitialized) { "The region has to be initialized." }
        val path = "/wows/ships/stats/"
        val extra = buildFieldString(FieldType.EXTRA, extraFields)
        val ships = if (shipIds.size == 0) "" else FieldType.SHIP_ID.toString() + shipIds.joinToString(",")
        return baseUrl(selectedRegion, path, language, instanceName) + FieldType.ACCOUNT_ID + accountId + extra + ships
    }

    enum class ExtraField(val key: String) : IResponseFields {
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
        fun createRequest(): StatisticsRequest {
            return StatisticsRequest()
        }
    }
}