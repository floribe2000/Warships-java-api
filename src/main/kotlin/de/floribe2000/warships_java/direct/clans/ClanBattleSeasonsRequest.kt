package de.floribe2000.warships_java.direct.clans

import de.floribe2000.warships_java.direct.api.AbstractRequest
import de.floribe2000.warships_java.direct.api.connect
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region

class ClanBattleSeasonsRequest : AbstractRequest<ClanBattleSeasonsRequest, ClanBattleSeasons>() {

    private lateinit var selectedRegion: Region

    private var language: Language? = null

    override fun region(region: Region): ClanBattleSeasonsRequest {
        this.selectedRegion = region
        return this
    }

    override fun language(language: Language): ClanBattleSeasonsRequest {
        this.language = language
        return this
    }

    override fun apiBuilder(instanceName: String): ClanBattleSeasonsRequest {
        setInstance(instanceName)
        return this
    }

    override fun buildUrl(): String {
        require(this::selectedRegion.isInitialized) { "The region has to be initialized." }
        val path = "/wows/clans/season/"

        return baseUrl(selectedRegion, path, language, instanceName)
    }

    override fun fetch(url: String): ClanBattleSeasons {
        return connect(url, limiter)
    }
}