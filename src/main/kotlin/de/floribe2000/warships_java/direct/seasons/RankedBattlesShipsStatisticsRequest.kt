package de.floribe2000.warships_java.direct.seasons

import de.floribe2000.warships_java.direct.api.AbstractRequest
import de.floribe2000.warships_java.direct.api.typeDefinitions.FieldType
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import org.slf4j.LoggerFactory

@Deprecated("Ranked Battle statistics have been changed by wargaming.")
@Suppress("UNUSED")
class RankedBattlesShipsStatisticsRequest : AbstractRequest<RankedBattlesShipsStatisticsRequest, RankedBattlesShipsStatistics>() {
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
     * The set of season IDs for this request
     */
    private var seasonIds: MutableSet<Int> = HashSet()

    /**
     * The set of season IDs for this request
     */
    private var accountIds: MutableSet<Long> = HashSet()

    /**
     * The set of season IDs for this request
     */
    private var shipIds: MutableSet<Long> = HashSet()

    override fun region(region: Region): RankedBattlesShipsStatisticsRequest {
        this.selectedRegion = region
        return this
    }

    override fun language(language: Language): RankedBattlesShipsStatisticsRequest {
        this.language = language
        return this
    }

    fun seasonIds(seasonIds: Collection<Int>): RankedBattlesShipsStatisticsRequest {
        require(seasonIds.size in 1..100) { "The collection must not be null and have between 1 and 100 entries" }
        this.seasonIds = HashSet(seasonIds)
        return this
    }

    fun addSeason(seasonId: Int): RankedBattlesShipsStatisticsRequest {
        if (seasonIds.size < 100) {
            seasonIds.add(seasonId)
        }
        return this
    }

    fun accountIds(accountIds: Collection<Long>): RankedBattlesShipsStatisticsRequest {
        require(accountIds.size in 1..100) { "The collection must not be null and have between 1 and 100 entries" }
        this.accountIds = HashSet(accountIds)
        return this
    }

    fun addAccountId(accountId: Long): RankedBattlesShipsStatisticsRequest {
        if (accountIds.size < 100) {
            accountIds.add(accountId)
        }
        return this
    }

    fun shipIds(shipIds: Collection<Long>): RankedBattlesShipsStatisticsRequest {
        require(shipIds.size in 1..100) { "The collection must not be null and have between 1 and 100 entries" }
        this.shipIds = HashSet(shipIds)
        return this
    }

    fun addShip(shipId: Long): RankedBattlesShipsStatisticsRequest {
        if (shipIds.size < 100) {
            shipIds.add(shipId)
        }
        return this
    }

    /**
     * Executes a request and returns the result of the request.
     *
     * @return an instance of [RankedBattlesShipsStatistics] that contains all requested data. If the
     * request fails, an empty instance is returned.
     * @throws IllegalArgumentException
     *  * If this method is called and region is null.
     *
     */
    override fun fetch(url: String): RankedBattlesShipsStatistics {
        return connectWithGson(url, RankedBattlesShipsStatistics::class.java, limiter)
    }

    override fun apiBuilder(instanceName: String): RankedBattlesShipsStatisticsRequest {
        setInstance(instanceName)
        return this
    }

    override fun buildUrl(): String {
        require(this::selectedRegion.isInitialized) { "The region has to be set" }
        require(accountIds.isNotEmpty()) { "At least one account ID has to be set" }
        val path = "/wows/seasons/shipstats/"
        val seasons = seasonIds.joinToString(", ")
        val ships = shipIds.joinToString(", ")
        val accounts = accountIds.joinToString(", ")
        return baseUrl(selectedRegion, path, language, instanceName) + FieldType.SEASON_ID + seasons + FieldType.SHIP_ID + ships + FieldType.ACCOUNT_ID + accounts
    }

    companion object {
        fun createRequest(): RankedBattlesShipsStatisticsRequest {
            return RankedBattlesShipsStatisticsRequest()
        }
    }
}