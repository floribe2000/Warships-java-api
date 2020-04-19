import static org.junit.Assert.assertEquals;

import de.floribe2000.warships_java.direct.api.ApiBuilder;
import de.floribe2000.warships_java.direct.api.Region;
import de.floribe2000.warships_java.direct.seasons.RankedBattlesPlayerStatisticsRequest;
import de.floribe2000.warships_java.direct.seasons.RankedBattlesPlayersStatistics;
import de.floribe2000.warships_java.direct.seasons.RankedBattlesSeasons;
import de.floribe2000.warships_java.direct.seasons.RankedBattlesSeasonsRequest;
import de.floribe2000.warships_java.direct.seasons.RankedBattlesShipsStatistics;
import de.floribe2000.warships_java.direct.seasons.RankedBattlesShipsStatisticsRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class SeasonsTest {

	private final Properties PROPERTIES = new Properties();
	private String apiKey = "";
	private int accountId = 540241530;

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
	public void testSeasonsRequest() {
		RankedBattlesSeasonsRequest request = RankedBattlesSeasonsRequest.createRequest()
			.region(Region.EU).addSeason(15);
		RankedBattlesSeasons result = request.fetch();
		System.out.println(result);
		assertEquals("ok", result.getStatus());
	}

	@Test
	public void testSeasonsPlayerStats() {
		RankedBattlesPlayerStatisticsRequest request = RankedBattlesPlayerStatisticsRequest
			.createRequest().region(Region.EU).addSeason(15).addAccountId(accountId);
		RankedBattlesPlayersStatistics result = request.fetch();
		System.out.println(result);
		assertEquals("ok", result.getStatus());
	}

	@Test
	public void testSeasonsShipStats() {
		RankedBattlesShipsStatisticsRequest request = RankedBattlesShipsStatisticsRequest
			.createRequest().region(Region.EU).addSeason(15).addAccountId(accountId);
		RankedBattlesShipsStatistics result = request.fetch();
		System.out.println(result);
		assertEquals("ok", result.getStatus());
	}
}
