package de.floribe2000.warships_java.direct.general

import de.floribe2000.warships_java.direct.api.AbstractRequest
import de.floribe2000.warships_java.direct.api.ApiBuilder.Companion.getApiKeyAsParam
import de.floribe2000.warships_java.direct.api.connect
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region

/**
 * A class to create a server status request.
 * Server status requests retrieve data about the current number of players on all available servers for a region.
 *
 * If you need data for multiple regions, call this request multiple times after changing the region.
 *
 * @author floribe2000
 */
class ServerStatusRequest : AbstractRequest<ServerStatusRequest, ServerStatus>() {
    /**
     * The server region for this request
     */
    private lateinit var selectedRegion: Region

    /**
     * The language for the api response
     */
    private var language: Language? = null

    override fun fetch(url: String): ServerStatus {
        return connect(url, limiter)
    }

    override fun region(region: Region): ServerStatusRequest {
        this.selectedRegion = region
        return this
    }

    override fun language(language: Language): ServerStatusRequest {
        this.language = language
        return this
    }

    override fun apiBuilder(instanceName: String): ServerStatusRequest {
        setInstance(instanceName)
        return this
    }

    override fun buildUrl(): String {
        require(this::selectedRegion.isInitialized) { "Region has to be initialized." }
        val path = "/wgn/servers/info/"
        return baseUrl(selectedRegion, path, language, instanceName)
    }

    override fun baseUrl(region: Region, path: String, language: Language?, instanceName: String?): String {
        val url = region.baseURL + path + getApiKeyAsParam(instanceName) + createLanguageField(language)
        return url.replace("api.worldofwarships.", "api.worldoftanks.")
    }

    companion object {
        fun createRequest(): ServerStatusRequest {
            return ServerStatusRequest()
        }
    }
}