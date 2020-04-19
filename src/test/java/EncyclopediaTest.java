import de.floribe2000.warships_java.direct.api.ApiBuilder;
import de.floribe2000.warships_java.direct.api.Region;
import de.floribe2000.warships_java.direct.encyclopedia.Warships;
import de.floribe2000.warships_java.direct.encyclopedia.WarshipsRequest;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EncyclopediaTest {

    private String apiKey;

    private final String instanceName = "TEST";

    public EncyclopediaTest() throws IOException {
        Properties PROPERTIES = new Properties();
        PROPERTIES.load(new FileInputStream("Warships.properties"));
        apiKey = PROPERTIES.getProperty("APIKEY");
    }

    @Test
    public void testWarshipsRequest() {
        ApiBuilder.createInstance(apiKey, instanceName);
        int limit = 100;
        int page = 5;
        Warships warships = WarshipsRequest.createRequest().region(Region.EU).limit(limit).pageNo(page).fetch();
        assert warships.getStatus().equals("ok") : warships;
        assert warships.getMeta() != null : warships;
        assert warships.getData() != null : warships;
        assert warships.getData().size() == warships.getMeta().getCount() && warships.getData().size() <= limit : warships;
    }
}
