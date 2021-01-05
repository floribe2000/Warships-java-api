package de.floribe2000.warships_java.direct.account

import de.floribe2000.warships_java.direct.api.AbstractRequest
import de.floribe2000.warships_java.direct.api.connect
import de.floribe2000.warships_java.direct.api.typeDefinitions.FieldType
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import org.slf4j.LoggerFactory
import java.util.*
import java.util.stream.Collectors

/**
 * A class to create and execute requests to retrieve details about players' achievements.
 *
 * Up to 100 players can be added to the request, the result of the request can be retrieved as an instance of [PlayersAchievements].
 *
 * @author floribe2000
 */
class PlayersAchievmentsRequest : AbstractRequest<PlayersAchievmentsRequest, PlayersAchievements>() {
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
     * The account id of the player
     */
    private var accountIds: MutableSet<Long> = HashSet()

    override fun region(region: Region): PlayersAchievmentsRequest {
        this.selectedRegion = region
        return this
    }

    override fun language(language: Language): PlayersAchievmentsRequest {
        this.language = language
        return this
    }

    override fun buildUrl(): String {
        require(this::selectedRegion.isInitialized && accountIds.size > 0) {
            "The region must not be null and the list of the account ids must not be empty."
        }
        val path = "/wows/account/achievements/"
        val ids = accountIds.stream().sequential().map { o: Long? -> Objects.toString(o) }.collect(Collectors.joining(","))
        return baseUrl(selectedRegion, path, language, instanceName) + FieldType.ACCOUNT_ID + ids
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
    fun accountIds(accountIds: Collection<Long>): PlayersAchievmentsRequest {
        require(accountIds.size <= 100) { "The size of the provided collection of ids exceeds the limit of 100 ids" }
        this.accountIds = HashSet(accountIds)
        return this
    }

    /**
     * Adds an account id to the list of account ids.
     *
     * Existing ids won't be changed!
     * If the limit is reached, the id won't be added to the request and a logging call is triggered.
     *
     * @param id the id to add
     * @return the instance of this request
     */
    fun addAccountId(id: Long): PlayersAchievmentsRequest {
        if (accountIds.size < 100) {
            accountIds.add(id)
        } else {
            log.warn("Skipping account id addition. Reason: Limit reached (Limit: 100)")
        }
        return this
    }

    /**
     * Executes a request and returns the result of the request.
     *
     * All requests are executed synchronous on this thread. It is safe to execute this in a new thread if it is required to be run asynchronous.
     *
     * @return an instance of [PlayersAchievements] that contains all requested player data. If the request fails, an empty instance is returned.
     * @throws IllegalArgumentException
     *  * If this method is called and region is null.
     *  * If this method is called and accountIds is empty.
     *
     */
    override fun fetch(url: String): PlayersAchievements {
        return connect(url, limiter)
    }

    override fun apiBuilder(instanceName: String): PlayersAchievmentsRequest {
        setInstance(instanceName)
        return this
    }

    companion object {
        /**
         * Creates a new empty request of this class.
         *
         * @return an instance of this class
         */
        fun createRequest(): PlayersAchievmentsRequest {
            return PlayersAchievmentsRequest()
        }
    }
}