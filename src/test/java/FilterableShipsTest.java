import de.floribe2000.warships_java.api.ApiBuilder;
import de.floribe2000.warships_java.api.Nation;
import de.floribe2000.warships_java.api.Region;
import de.floribe2000.warships_java.api.ShipType;
import de.floribe2000.warships_java.encyclopedia.Warships;
import de.floribe2000.warships_java.encyclopedia.WarshipsRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;

public class FilterableShipsTest {

	private final Properties PROPERTIES = new Properties();
	private String apiKey = "";
	private int accountId = 540241530;

	private final String instanceName = "TEST";

	public FilterableShipsTest() throws IOException {
		PROPERTIES.load(new FileInputStream("Warships.properties"));
		apiKey = PROPERTIES.getProperty("APIKEY");
	}

	@Before
	public void init() {
		ApiBuilder.createInstance(apiKey, instanceName);
	}

	@Test
	public void test() {
		WarshipsRequest request = WarshipsRequest.createRequest().region(Region.EU).nation(Nation.USA);
		Warships result = request.fetch();
		System.out.println(result.getData().keySet().stream().map(Object::toString).collect(Collectors.joining(", ")));
	}

}
