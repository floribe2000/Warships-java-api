package de.floribe2000.warships_java.utilities

import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import de.floribe2000.warships_java.direct.encyclopedia.Warships
import de.floribe2000.warships_java.direct.encyclopedia.WarshipsRequest.Companion.createRequest

/**
 * A class that offers a collection of utility methods for api encyclopedia requests.
 *
 * Make sure to initialize the ApiBuilder instance before sending requests! You can either do this by using [.initialize]
 * or by manually creating an ApiBuilder instance using [de.floribe2000.warships_java.direct.api.ApiBuilder.createInstance]
 *
 * @author floribe2000
 */
object EncyclopediaRequestService : AbstractRequestService() {

    /**
     * Allows to request a full list of all ships currently in the game.
     *
     * Ignore the page number of the meta object of the returned [Warships] instance!
     *
     * @param region   the region for the request
     * @param language the language for the request
     * @return a [Warships] instance containing the data
     */
    @JvmOverloads
    fun requestFullWarshipsList(region: Region, language: Language = Language.ENGLISH): Warships {
        //Prepare basic request
        val request = createRequest().region(region).language(language)
        //Send first request
        val result = request.fetch()
        //Check response status
        check(result.status.get()) { "Invalid result state!" }

        var page = 1
        while (page < result.meta.page_total) {
            page++
            val tmp = request.pageNo(page).fetch()
            if (tmp.status.get()) {
                result.data?.putAll(tmp.data ?: mutableMapOf())
            } else {
                throw IllegalStateException("Invalid result state!")
            }
        }
        return result
    }

    override fun initialize(apiKey: String) {
        AbstractRequestService.initialize(apiKey)
    }
}