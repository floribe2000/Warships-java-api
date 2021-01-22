package de.floribe2000.warships_java.direct.seasons

import de.floribe2000.warships_java.direct.api.AbstractRequest
import de.floribe2000.warships_java.direct.api.connectWithGson
import de.floribe2000.warships_java.direct.api.typeDefinitions.FieldType
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import java.util.*

@Suppress("unused")
@Deprecated("Ranked Battle statistics have been changed by wargaming.")
class RankedBattlesSeasonsRequest : AbstractRequest<RankedBattlesSeasonsRequest, RankedBattlesSeasons>() {
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

    override fun region(region: Region): RankedBattlesSeasonsRequest {
        this.selectedRegion = region
        return this
    }

    override fun language(language: Language): RankedBattlesSeasonsRequest {
        this.language = language
        return this
    }

    fun seasonIds(seasonIds: Collection<Int>): RankedBattlesSeasonsRequest {
        require(seasonIds.size in 1..100) { "The collection must not be null and have between 1 and 100 entries" }
        this.seasonIds = HashSet(seasonIds)
        return this
    }

    fun addSeason(seasonId: Int): RankedBattlesSeasonsRequest {
        if (seasonIds.size < 100) {
            seasonIds.add(seasonId)
        }
        return this
    }

    /**
     * Executes a request and returns the result of the request.
     *
     * @return an instance of [RankedBattlesSeasons] that contains all requested data. If the
     * request fails, an empty instance is returned.
     * @throws IllegalArgumentException
     *  * If this method is called and region is null.
     *
     */
    override fun fetch(url: String): RankedBattlesSeasons {
        return connectWithGson(url, limiter)
    }

    override fun apiBuilder(instanceName: String): RankedBattlesSeasonsRequest {
        setInstance(instanceName)
        return this
    }

    override fun buildUrl(): String {
        require(this::selectedRegion.isInitialized) { "The region has to be set" }
        val path = "/wows/seasons/info/"
        val seasons = seasonIds.joinToString(", ")
        return baseUrl(selectedRegion, path, language, instanceName) + FieldType.SEASON_ID + seasons
    }

    companion object {
        fun createRequest(): RankedBattlesSeasonsRequest {
            return RankedBattlesSeasonsRequest()
        }
    }
}