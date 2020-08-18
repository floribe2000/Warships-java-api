import de.floribe2000.warships_java.direct.account.PlayersPersonalDataFull;
import de.floribe2000.warships_java.direct.account.PlayersPersonalDataFullRequest;
import de.floribe2000.warships_java.direct.api.ApiBuilder;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region;
import de.floribe2000.warships_java.direct.general.ServerStatus;
import de.floribe2000.warships_java.direct.general.ServerStatusRequest;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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
        int accountId = 537376379;
        ApiBuilder.createInstance(apiKey, instanceName);
        int instanceSize = ApiBuilder.getInstanceSize();
        PlayersPersonalDataFullRequest request = PlayersPersonalDataFullRequest.createRequest().region(Region.EU).addAccountId(accountId);
        ExecutorService service = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();
        int requests = 100;
        for (int i = 0; i < requests; i++) {
            service.execute(() -> {
                PlayersPersonalDataFull result = request.fetch();
                if (result == null) {
                    System.out.println("Result was null");
                }
                assert result != null : "Result was null";
                assert result.getStatus().get() : result.getError().getMessage();
            });
        }
        service.shutdown();
        try {
            service.awaitTermination(20, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        double time = (double) (System.currentTimeMillis() - start) / 1000;
        assert time <= (((double) requests / 10) * 1.3) + 1 : time;
        System.out.println("Time passed: " + time + "s");
        assert ApiBuilder.getInstanceSize() == instanceSize : ApiBuilder.getInstanceSize() + ", expected size of " + instanceSize;
    }

    @Test
    public void testShutdown() {
        ApiBuilder.createInstance(apiKey);
        assert ApiBuilder.getApiKeyAsParam(null) != null : "Api key is null";
        ApiBuilder.shutdown();
        assert ApiBuilder.getInstanceSize() == 0 : "Instance list not cleared after shutdown";
        ApiBuilder.createInstance(apiKey, "NEWAPP");
        assert ApiBuilder.getApiKeyAsParam(null) != null : "Api key is null";
        ApiBuilder.shutdown();
    }

    @Test
    public void testServerStatus() {
        ApiBuilder.createInstanceIfNoneExists(apiKey);
        ServerStatusRequest request = ServerStatusRequest.createRequest().region(Region.EU);
        ServerStatus response = request.fetch();
        assert response.getStatus().get() : "Invalid response state\n" + response.getError().toString();
        System.out.println(response);
    }
}
