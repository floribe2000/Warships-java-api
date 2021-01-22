package de.floribe2000.warships_java.direct.account

import de.floribe2000.warships_java.direct.api.AbstractRequest
import de.floribe2000.warships_java.direct.api.connect
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import org.slf4j.Logger
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
    override val log: Logger = LoggerFactory.getLogger(this::class.simpleName)

    /**
     * The server region for this request
     */
    private lateinit var selectedRegion: Region

    /**
     * The language for the api response
     */
    private var language: Language? = null

    /**
     * The search text for this request
     */
    private lateinit var selectedSearchText: String

    override fun region(region: Region): PlayersRequest {
        this.selectedRegion = region
        return this
    }

    override fun language(language: Language): PlayersRequest {
        this.language = language
        return this
    }

    override fun buildUrl(): String {
        require(this::selectedRegion.isInitialized && this::selectedSearchText.isInitialized) {
            "You can't use this method before setting all parameters"
        }
        val path = "/wows/account/list/"
        return baseUrl(selectedRegion, path, language, instanceName) + "&search=" + selectedSearchText
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
    fun searchText(text: String): PlayersRequest {
        selectedSearchText = text
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
        return connect(url, limiter)
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