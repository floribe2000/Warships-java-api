import de.floribe2000.warships_java.account.*;
import de.floribe2000.warships_java.api.ApiBuilder;
import de.floribe2000.warships_java.api.Region;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import de.floribe2000.warships_java.warships.Statistics;
import de.floribe2000.warships_java.warships.StatisticsRequest;
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
        assert result.getStatus().equals("ok") : result;
    }

    @Test
    public void testPlayerPersonalDataRequest() {
        //TODO
        ApiBuilder.createInstance(apiKey, instanceName);
        PlayersPersonalDataFull result = PlayersPersonalDataFullRequest.createRequest().region(Region.EU).addAccountId(537376379)
                .addExtraField(PlayersPersonalDataFullRequest.ExtraField.PVE)
                .addExtraField(PlayersPersonalDataFullRequest.ExtraField.RANK_SOLO).fetch();
        assert result.getStatus().equals("ok") : result;
    }

    @Test
    public void testPlayersAchievments() {
        ApiBuilder.createInstance(apiKey, instanceName);
        PlayersAchievments result = PlayersAchievmentsRequest.createRequest().region(Region.EU).accountId(537376379).fetch();
        assert result.getStatus().equals("ok") : result;
        assert PlayersAchievmentsRequest.AchievmentElement.retrieveElement("Solo Warrior") != null;
    }

    @Test
    public void testPlayersStatisticsByDate() {
        ApiBuilder.createInstance(apiKey, instanceName);
        PlayersStatisticsByDate result1 = PlayerStatisticsByDateRequest.createRequest().region(Region.EU).accountId(537376379).addDate("20200318").fetch();
        assert result1.getStatus().equals("ok") : result1;
        PlayersStatisticsByDate result = PlayerStatisticsByDateRequest.createRequest().region(Region.EU).accountId(537376379).addDate("20200228").addDate("20200118")
                .extra(PlayerStatisticsByDateRequest.ExtraField.PVE).fetch();
        assert result.getStatus().equals("ok") : result;
    }

    @Test
    public void testPlayersWarshipsStatistics() {
        ApiBuilder.createInstance(apiKey, instanceName);
        Statistics result = StatisticsRequest.createRequest().region(Region.EU).accountId(537376379).addExtraField(StatisticsRequest.ExtraField.PVE).fetch();
        assert result.getStatus().equals("ok") : result;
    }

    @Test
    public void testRateLimiter() {
        int accountId = 537376379;
        ApiBuilder.createInstance(apiKey, instanceName);
        PlayersPersonalDataFullRequest request = PlayersPersonalDataFullRequest.createRequest().region(Region.EU).addAccountId(accountId);
        ExecutorService service = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 40; i++) {
            service.execute(() -> {
                PlayersPersonalDataFull result = request.fetch();
                assert result.getStatus().equals("ok") : result;
            });
        }
        service.shutdown();
        try {
            service.awaitTermination(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println((double) (System.currentTimeMillis() - start) / 1000);
    }

    @Test
    public void testInstanceCount() {
        assert ApiBuilder.getInstanceSize() == 1 : ApiBuilder.getInstanceSize();
    }

}
