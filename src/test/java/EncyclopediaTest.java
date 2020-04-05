import de.floribe2000.warships_java.api.ApiBuilder;
import de.floribe2000.warships_java.api.Region;
import de.floribe2000.warships_java.encyclopedia.Warships;
import de.floribe2000.warships_java.encyclopedia.WarshipsRequest;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EncyclopediaTest {

    private final Properties PROPERTIES = new Properties();
    private String apiKey = "";

    private final String instanceName = "TEST";

    public EncyclopediaTest() throws IOException {
        PROPERTIES.load(new FileInputStream("Warships.properties"));
        apiKey = PROPERTIES.getProperty("APIKEY");
    }

    @Test
    public void testWarshipsRequest() {
        ApiBuilder.createInstance(apiKey, instanceName);
        int limit = 100;
        int page = 5;
        Warships warships = WarshipsRequest.createRequest().region(Region.EU).limit(limit).pageNo(page).fetch();
        System.out.println(warships);
        assert warships.getStatus().equals("ok");
        assert warships.getMeta() != null;
        assert warships.getData() != null;
        assert warships.getData().size() == warships.getMeta().getCount() && warships.getData().size() <= limit;
    }
}
