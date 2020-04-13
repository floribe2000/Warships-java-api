import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import de.floribe2000.warships_java.api.ApiBuilder;
import de.floribe2000.warships_java.api.Region;
import de.floribe2000.warships_java.encyclopedia.Warships;
import de.floribe2000.warships_java.encyclopedia.WarshipsRequest;
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
        assertEquals("ok", warships.getStatus());
        assertNotNull(warships.getMeta());
        assertNotNull(warships.getData());
        assertEquals(warships.getData().size(), warships.getMeta().getCount());
        assertTrue(warships.getData().size() <= limit);
    }
}
