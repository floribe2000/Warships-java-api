import de.floribe2000.warships_java.direct.account.PlayersPersonalDataFull;
import de.floribe2000.warships_java.direct.api.ApiBuilder;
import de.floribe2000.warships_java.direct.api.Region;
import de.floribe2000.warships_java.direct.encyclopedia.Warships;
import de.floribe2000.warships_java.direct.encyclopedia.WarshipsRequest;
import de.floribe2000.warships_java.utilities.EncyclopediaRequestService;
import de.floribe2000.warships_java.utilities.PlayerRequestService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UtilityTest {

    private final String apiKey;

    public UtilityTest() throws IOException {
        Properties PROPERTIES = new Properties();
        PROPERTIES.load(new FileInputStream("Warships.properties"));
        apiKey = PROPERTIES.getProperty("APIKEY");
    }

    @BeforeClass
    public static void prepareTests() throws IOException {
        ApiBuilder.shutdown();
    }

    @AfterClass
    public static void shutDownTests() throws IOException {
        ApiBuilder.shutdown();
    }

    @Test
    public void testShipRequestService() {
        EncyclopediaRequestService.initialize(apiKey);
        Warships warships = WarshipsRequest.createRequest().region(Region.EU).fetch();
        assert warships.getStatus().get() : "Invalid response status";
        Warships fullList = EncyclopediaRequestService.requestFullWarshipsList(Region.EU);
        assert fullList.getStatus().get() : "Invalid response status from combined list";
        assert fullList.getData().size() == warships.getMeta().getTotal() :
                "Size of retrieved list does not match expected size. Expected " + warships.getMeta().getTotal() + ", got " + fullList.getData().size();
    }

    @Test
    public void testPlayerRequestService() {
        PlayerRequestService.initialize(apiKey);
        String playerName = "floribe2000";
        PlayersPersonalDataFull player = PlayerRequestService.requestPlayersPersonalData(playerName, Region.EU);
        assert player.getStatus().get() : "Invalid response status";
        assert player.getData().size() > 0 : "Empty response";
        String playerId = player.getData().keySet().iterator().next();
        assert player.getData().get(playerId).getNickname().equals(playerName) :
                "Result does not match search name. Expected " + playerName + ", got " + player.getData().get(playerId).getNickname();
    }
}
