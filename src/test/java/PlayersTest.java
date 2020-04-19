import de.floribe2000.warships_java.direct.account.*;
import de.floribe2000.warships_java.direct.api.ApiBuilder;
import de.floribe2000.warships_java.direct.api.Region;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import de.floribe2000.warships_java.direct.warships.Statistics;
import de.floribe2000.warships_java.direct.warships.StatisticsRequest;
import org.junit.Test;

public class PlayersTest {

    private String apiKey;

    private final String instanceName = "TEST";

    public PlayersTest() throws IOException {
        Properties PROPERTIES = new Properties();
        PROPERTIES.load(new FileInputStream("Warships.properties"));
        apiKey = PROPERTIES.getProperty("APIKEY");
    }

    @Test
    public void testPlayersRequest() {
        ApiBuilder.createInstance(apiKey, instanceName);
        PlayersRequest request = PlayersRequest.createRequest().region(Region.EU).searchText("floribe");
        Players result = request.fetch();
        assert result.getStatus().get() : result;
    }

    @Test
    public void testPlayerPersonalDataRequest() {
        //TODO
        ApiBuilder.createInstance(apiKey, instanceName);
        PlayersPersonalDataFull result = PlayersPersonalDataFullRequest.createRequest().region(Region.EU).addAccountId(537376379)
                .addExtraField(PlayersPersonalDataFullRequest.ExtraField.PVE)
                .addExtraField(PlayersPersonalDataFullRequest.ExtraField.RANK_SOLO).fetch();
        assert result.getStatus().get() : result;
    }

    @Test
    public void testPlayersAchievments() {
        ApiBuilder.createInstance(apiKey, instanceName);
        PlayersAchievments result = PlayersAchievmentsRequest.createRequest().region(Region.EU).accountId(537376379).fetch();
        assert result.getStatus().get() : result;
        assert PlayersAchievmentsRequest.AchievmentElement.retrieveElement("Solo Warrior") != null;
    }

    @Test
    public void testPlayersStatisticsByDate() {
        ApiBuilder.createInstance(apiKey, instanceName);
        PlayersStatisticsByDate result1 = PlayerStatisticsByDateRequest.createRequest().region(Region.EU).accountId(537376379).addDate("20200318").fetch();
        assert result1.getStatus().get() : result1;
        PlayersStatisticsByDate result = PlayerStatisticsByDateRequest.createRequest().region(Region.EU).accountId(537376379).addDate("20200228").addDate("20200118")
                .extra(PlayerStatisticsByDateRequest.ExtraField.PVE).fetch();
        assert result.getStatus().get() : result;
    }

    @Test
    public void testPlayersWarshipsStatistics() {
        ApiBuilder.createInstance(apiKey, instanceName);
        Statistics result = StatisticsRequest.createRequest().region(Region.EU).accountId(537376379).addExtraField(StatisticsRequest.ExtraField.PVE).fetch();
        assert result.getStatus().get() : result;
    }

    @Test
    public void testInstanceCount() {
        assert ApiBuilder.getInstanceSize() == 1 : ApiBuilder.getInstanceSize();
    }

}
