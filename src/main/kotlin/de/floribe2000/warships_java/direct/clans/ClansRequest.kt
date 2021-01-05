package de.floribe2000.warships_java.direct.clans

import de.floribe2000.warships_java.direct.api.AbstractRequest
import de.floribe2000.warships_java.direct.api.connect
import de.floribe2000.warships_java.direct.api.typeDefinitions.FieldType
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import org.slf4j.LoggerFactory

/**
 * A class to create and execute requests to retrieve a list of clans based on their name or tag.
 *
 * Up to 100 clans can be retrieved in one request, if there are more matches you have to execute the request multiple times while increasing the page number.
 * Details about the total amount of hits can be retrieved by analyzing the [Meta][de.floribe2000.warships_java.direct.api.Meta] object of the api response
 * returned by [.fetch].
 *
 * @author floribe2000
 */
class ClansRequest private constructor() : AbstractRequest<ClansRequest, Clans>() {
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
     * The search text for this request
     */
    private lateinit var selectedSearchText: String

    /**
     * The page for this request. Minimum value 1, maximum value undefined.
     */
    private var page = 1

    override fun region(region: Region): ClansRequest {
        this.selectedRegion = region
        return this
    }

    override fun language(language: Language): ClansRequest {
        this.language = language
        return this
    }

    override fun apiBuilder(instanceName: String): ClansRequest {
        setInstance(instanceName)
        return this
    }

    /**
     * Sets the search text for this request.
     *
     * Replaces existing values! No validity check in this method!
     * If the search string is invalid, calling [.fetch] will throw an Exception.
     *
     * @param searchText the search string to set
     * @return the instance of this request
     */
    fun search(searchText: String): ClansRequest {
        this.selectedSearchText = searchText
        return this
    }

    /**
     * Sets the page number for this request.
     *
     * Replaces existing values. Minimum value is 1, maximum value is not defined.
     *
     * @param page the new value for the request page
     * @return the instance of this request
     * @throws IllegalArgumentException If the value is less than 1.
     */
    fun page(page: Int): ClansRequest {
        return if (page > 0) {
            this.page = page
            this
        } else {
            throw IllegalArgumentException("Page must not be less than 1")
        }
    }

    /**
     * Executes a request and returns the result of the request.
     *
     * All requests are executed synchronous on this thread. It is safe to execute this in a new thread if it is required to be run asynchronous.
     *
     * @return an instance of [Clans] that contains all requested player data. If the request fails, an empty instance is returned.
     * @throws IllegalArgumentException
     *  * If this method is called and region is null.
     *  * If this method is called and search string is null.
     *  * If this method is called and the length of the search string contains less than 2 characters-
     *
     */
    override fun fetch(url: String): Clans {
        return connect(url, limiter)
    }

    override fun buildUrl(): String {
        require(this::selectedRegion.isInitialized && this::selectedSearchText.isInitialized && selectedSearchText.length >= 2) {
            "Search text has to be at least 2 chars long and region must not be null"
        }
        val path = "/wows/clans/list/"
        return baseUrl(selectedRegion, path, language, instanceName) + FieldType.SEARCH + selectedSearchText + FieldType.PAGE + page
    }

    companion object {
        /**
         * Creates a new empty request of this class.
         *
         * @return an instance of this class
         */
        fun createRequest(): ClansRequest {
            return ClansRequest()
        }
    }
}