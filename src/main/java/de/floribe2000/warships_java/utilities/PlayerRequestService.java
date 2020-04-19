package de.floribe2000.warships_java.utilities;

import de.floribe2000.warships_java.direct.account.Players;
import de.floribe2000.warships_java.direct.account.PlayersPersonalDataFull;
import de.floribe2000.warships_java.direct.account.PlayersPersonalDataFullRequest;
import de.floribe2000.warships_java.direct.account.PlayersRequest;
import de.floribe2000.warships_java.direct.api.ApiBuilder;
import de.floribe2000.warships_java.direct.api.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class PlayerRequestService {

    private static boolean initialized = false;

    private static final String INSTANCE = "PlayerRequestService";

    private static final Logger LOG = LoggerFactory.getLogger(PlayerRequestService.class.getSimpleName());

    public static void initialize(String apiKey) {
        ApiBuilder.createInstance(apiKey, INSTANCE);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                ApiBuilder.shutdown();
            } catch (IOException e) {
                LOG.error("An exception occurred while shutting down the api instance.", e);
            }
        }));
    }

    public static PlayersPersonalDataFull requestPlayersPersonalData(String playerName, Region region) {
        Players players = PlayersRequest.createRequest().region(region).searchText(playerName).fetch();
        if (!players.getStatus().equals("ok")) {
            throw new IllegalStateException("The request failed!");
        }
        if (players.getData().size() < 1) {
            throw new IllegalStateException("Empty api response for this request. No data found.");
        }
        int id = players.getData().get(0).getAccount_id();
        PlayersPersonalDataFullRequest request = PlayersPersonalDataFullRequest.createRequest().region(region).addAccountId(id);
        PlayersPersonalDataFull playerData = request.fetch();

        return null;
    }
}
