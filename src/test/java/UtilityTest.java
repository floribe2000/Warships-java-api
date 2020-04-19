import de.floribe2000.warships_java.direct.api.ApiBuilder;
import de.floribe2000.warships_java.direct.api.Region;
import de.floribe2000.warships_java.direct.encyclopedia.Warships;
import de.floribe2000.warships_java.direct.encyclopedia.WarshipsRequest;
import de.floribe2000.warships_java.utilities.EncyclopediaRequestService;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UtilityTest {

    private String apiKey;

    private final String instanceName = "TEST";

    public UtilityTest() throws IOException {
        Properties PROPERTIES = new Properties();
        PROPERTIES.load(new FileInputStream("Warships.properties"));
        apiKey = PROPERTIES.getProperty("APIKEY");
    }

    @Test
    public void testShipRequestService() {
        ApiBuilder.createInstance(apiKey, instanceName);
        Warships warships = WarshipsRequest.createRequest().region(Region.EU).fetch();
        assert warships.getStatus().get() : "Invalid response status";
        Warships fullList = EncyclopediaRequestService.requestFullWarshipsList(Region.EU);
        assert fullList.getStatus().get() : "Invalid response status from combined list";
        assert fullList.getData().size() == warships.getMeta().getTotal() :
                "Size of retrieved list does not match expected size. Expected " + warships.getMeta().getTotal() + ", got " + fullList.getData().size();
    }
}
