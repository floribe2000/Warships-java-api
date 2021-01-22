package de.floribe2000.warships_java.direct.seasons

import de.floribe2000.warships_java.direct.api.AbstractRequest
import de.floribe2000.warships_java.direct.api.connectWithGson
import de.floribe2000.warships_java.direct.api.typeDefinitions.FieldType
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region

@Suppress("unused")
class RankedBattlesPlayerStatisticsRequest : AbstractRequest<RankedBattlesPlayerStatisticsRequest, RankedBattlesPlayersStatistics>() {
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

    override fun region(region: Region): RankedBattlesPlayerStatisticsRequest {
        this.selectedRegion = region
        return this
    }

    override fun language(language: Language): RankedBattlesPlayerStatisticsRequest {
        this.language = language
        return this
    }

    fun seasonIds(seasonIds: Collection<Int>): RankedBattlesPlayerStatisticsRequest {
        require(seasonIds.size in 1..100) { "The collection must not be null and have between 1 and 100 entries" }
        this.seasonIds = HashSet(seasonIds)
        return this
    }

    fun addSeason(seasonId: Int): RankedBattlesPlayerStatisticsRequest {
        if (seasonIds.size < 100) {
            seasonIds.add(seasonId)
        }
        return this
    }

    fun accountIds(accountIds: Collection<Long>): RankedBattlesPlayerStatisticsRequest {
        require(accountIds.size in 1..100) { "The collection must not be null and have between 1 and 100 entries" }
        this.accountIds = HashSet(accountIds)
        return this
    }

    fun addAccountId(accountId: Long): RankedBattlesPlayerStatisticsRequest {
        if (accountIds.size < 100) {
            accountIds.add(accountId)
        }
        return this
    }

    /**
     * Executes a request and returns the result of the request.
     *
     * @return an instance of [RankedBattlesPlayersStatistics] that contains all requested data. If the
     * request fails, an empty instance is returned.
     * @throws IllegalArgumentException
     *  * If this method is called and region is null.
     *
     */
    override fun fetch(url: String): RankedBattlesPlayersStatistics {
        return connectWithGson(url, limiter)
    }

    override fun apiBuilder(instanceName: String): RankedBattlesPlayerStatisticsRequest {
        setInstance(instanceName)
        return this
    }

    override fun buildUrl(): String {
        require(this::selectedRegion.isInitialized) { "The region has to be set" }
        require(accountIds.isNotEmpty()) { "At least one account ID has to be set" }
        val path = "/wows/seasons/accountinfo/"
        val seasons = seasonIds.joinToString(",")
        val accounts = accountIds.joinToString(",")
        return baseUrl(selectedRegion, path, language, instanceName) + FieldType.SEASON_ID + seasons + FieldType.ACCOUNT_ID + accounts
    }
}