package de.floribe2000.warships_java.direct.encyclopedia

import de.floribe2000.warships_java.direct.api.AbstractRequest
import de.floribe2000.warships_java.direct.api.connect
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region

class MapsRequest : AbstractRequest<MapsRequest, Maps>() {
    /**
     * The server region for this request
     */
    private lateinit var selectedRegion: Region

    /**
     * The language for the api response
     */
    private var language: Language? = null

    override fun apiBuilder(instanceName: String): MapsRequest {
        setInstance(instanceName)
        return this
    }

    override fun region(region: Region): MapsRequest {
        this.selectedRegion = region
        return this
    }

    override fun language(language: Language): MapsRequest {
        this.language = language
        return this
    }

    /**
     * Executes a request and returns the result of the request.
     *
     * All requests are executed synchronous on this thread. It is safe to execute this in a new thread if it is required to be run asynchronous.
     *
     * @return an instance of [Maps] that contains all requested map data. If the request fails, an empty instance is returned.
     * @throws IllegalArgumentException If this method is called and region is null.
     */
    override fun fetch(url: String): Maps {
        return connect(url, limiter)
    }

    override fun buildUrl(): String {
        require(this::selectedRegion.isInitialized) { "The region has to be initialized." }
        val path = "/wows/encyclopedia/battlearenas/"
        return baseUrl(selectedRegion, path, language, instanceName)
    }

    companion object {
        /**
         * Creates a new empty request of this class.
         *
         * @return an instance of this class
         */
        fun createRequest(): MapsRequest {
            return MapsRequest()
        }
    }
}