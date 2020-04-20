import de.floribe2000.warships_java.direct.account.PlayersPersonalDataFull;
import de.floribe2000.warships_java.direct.account.PlayersPersonalDataFullRequest;
import de.floribe2000.warships_java.direct.api.ApiBuilder;
import de.floribe2000.warships_java.direct.api.Region;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GeneralTest {

    private String apiKey;

    private final String instanceName = "TEST";

    public GeneralTest() throws IOException {
        Properties PROPERTIES = new Properties();
        PROPERTIES.load(new FileInputStream("Warships.properties"));
        apiKey = PROPERTIES.getProperty("APIKEY");
    }

    @Test
    public void testRateLimiter() {
        int instanceSize = ApiBuilder.getInstanceSize();
        int accountId = 537376379;
        ApiBuilder.createInstance(apiKey, instanceName);
        PlayersPersonalDataFullRequest request = PlayersPersonalDataFullRequest.createRequest().region(Region.EU).addAccountId(accountId);
        ExecutorService service = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();
        int requests = 30;
        for (int i = 0; i < requests; i++) {
            service.execute(() -> {
                PlayersPersonalDataFull result = request.fetch();
                assert result.getStatus().get() : result;
            });
        }
        service.shutdown();
        try {
            service.awaitTermination(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        double time = (double) (System.currentTimeMillis() - start) / 1000;
        assert time <= (((double) requests / 10) * 1.3) + 1 : time;

        assert ApiBuilder.getInstanceSize() == instanceSize : ApiBuilder.getInstanceSize() + ", expected size of " + instanceSize;
    }
}
