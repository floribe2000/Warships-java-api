package de.floribe2000.warships_java.utilities

import de.floribe2000.warships_java.direct.account.PlayersPersonalDataFull
import de.floribe2000.warships_java.direct.account.PlayersPersonalDataFullRequest
import de.floribe2000.warships_java.direct.account.PlayersRequest
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region

object PlayerRequestService : AbstractRequestService() {

    fun requestPlayersPersonalData(playerName: String, region: Region = Region.EU): PlayersPersonalDataFull {
        val players = PlayersRequest.createRequest().region(region).searchText(playerName).fetch()
        check(players.status.get()) { "The request failed!" }
        check(players.data?.isNotEmpty() ?: false) { "Empty api response for this request. No data found." }

        val id = players.data?.get(0)?.accountId
        val request = PlayersPersonalDataFullRequest.createRequest().region(region).addAccountId(id ?: 0)
        val playerData = request.fetch()
        check(playerData.status.get()) { "Invalid response status!" }
        return playerData
    }
}