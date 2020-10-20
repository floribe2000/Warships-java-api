import static de.floribe2000.warships_java.direct.api.typeDefinitions.Status.OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import de.floribe2000.warships_java.direct.api.ApiBuilder;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Nation;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region;
import de.floribe2000.warships_java.direct.api.typeDefinitions.ShipCategory;
import de.floribe2000.warships_java.direct.api.typeDefinitions.ShipType;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Tier;
import de.floribe2000.warships_java.direct.warships.Statistics;
import de.floribe2000.warships_java.direct.warships.Statistics.ShipEntry;
import de.floribe2000.warships_java.direct.warships.StatisticsRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

public class StatisticsTest {

	private final long wargamingId = 540241530;
	private final long kii = 3762239184L;
	private final static String instanceName = "TEST";

	@BeforeClass
	public static void init() throws IOException {
		Properties PROPERTIES = new Properties();
		PROPERTIES.load(new FileInputStream("Warships.properties"));
		String apiKey = PROPERTIES.getProperty("APIKEY");
		ApiBuilder.Companion.createInstance(apiKey, instanceName);
	}

	@Test
	public void testShipStats() {
		StatisticsRequest request = StatisticsRequest.createRequest()
			.accountId(wargamingId).region(Region.EU).addShipId(kii);
		Statistics response = request.fetch();

		assertNotNull(response);
		assertEquals(OK, response.getStatus());

		List<ShipEntry> ships = response.getData().get(wargamingId);
		assertNotNull(ships);
		assertFalse(ships.isEmpty());

		assertEquals(kii, ships.get(0).getShip_id());
		assertNotNull(ships.get(0).getPvp());
		assertNull(ships.get(0).getPve());
	}

	@Test
	public void testShipStatsFiltered() {
		StatisticsRequest request = StatisticsRequest.createRequest()
				.accountId(wargamingId).region(Region.EU)
				.shipTypes(ShipType.CRUISER)
				.tiers(Tier.VIII, Tier.IX, Tier.X)
				.nations(Nation.USA)
				.categories(ShipCategory.RESEARCH);
		Statistics response = request.fetch();

		assertNotNull(response);
		assert response.getStatus().get() : response;
		//assertEquals(OK, response.getStatus());
		List<ShipEntry> ships = response.getData().get(wargamingId);
		assertNotNull(ships);
		assert !ships.isEmpty() : ships;

		// So far, there are 6 researchable US navy cruisers on tier VIII - X (CLs and CAs)
		// But actually I never player the CA VIII and IX
		assertTrue(ships.size() >= 4);
		assertNotNull(ships.get(0).getPvp());
	}
}
