import de.floribe2000.warships_java.api.ApiBuilder;
import de.floribe2000.warships_java.api.Region;
import de.floribe2000.warships_java.seasons.RankedBattlesSeasons;
import de.floribe2000.warships_java.seasons.RankedBattlesSeasonsRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;

public class SeasonsTest {

	private final Properties PROPERTIES = new Properties();
	private String apiKey = "";

	private final String instanceName = "TEST";

	public SeasonsTest() throws IOException {
		PROPERTIES.load(new FileInputStream("Warships.properties"));
		apiKey = PROPERTIES.getProperty("APIKEY");
	}

	@Before
	public void init() {
		ApiBuilder.createInstance(apiKey, instanceName);
	}

	@Test
	public void testSeasonsRequestS15EU() {
		RankedBattlesSeasonsRequest request = RankedBattlesSeasonsRequest.createRequest().region(
			Region.EU).addSeason(15);
		RankedBattlesSeasons result = request.fetch();
		System.out.println(result);
		assert result.getStatus().equals("ok");
	}
}
