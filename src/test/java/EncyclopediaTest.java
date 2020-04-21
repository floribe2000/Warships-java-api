import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import de.floribe2000.warships_java.direct.api.*;
import de.floribe2000.warships_java.direct.encyclopedia.Consumables;
import de.floribe2000.warships_java.direct.encyclopedia.ConsumablesRequest;
import de.floribe2000.warships_java.direct.encyclopedia.Warships;
import de.floribe2000.warships_java.direct.encyclopedia.WarshipsRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

public class EncyclopediaTest {

	private final static String instanceName = "TEST";

	@BeforeClass
	public static void init() throws IOException {
		Properties PROPERTIES = new Properties();
		PROPERTIES.load(new FileInputStream("Warships.properties"));
		String apiKey = PROPERTIES.getProperty("APIKEY");
		ApiBuilder.createInstance(apiKey, instanceName);
	}

	@Test
	public void testWarWarshipsRequest() {
		int limit = 100;
		int page = 5;
		Warships warships = WarshipsRequest.createRequest().region(Region.EU).limit(limit)
			.pageNo(page).fetch();

		assert warships.getStatus().get() : warships;
		assert warships.getMeta() != null : warships;
		assert warships.getData() != null : warships;
		assert warships.getData().size() == warships.getMeta().getCount()
			&& warships.getData().size() <= limit : warships;
	}

	@Test
	public void testShipRequestFilterClassCruisers() {
		WarshipsRequest request = WarshipsRequest.createRequest().region(Region.EU).shipType(ShipType.CRUISER);
		Warships response;
		int pageNo = 1;
		do {
			response = request.pageNo(pageNo).fetch();
			assertNotNull(response);
			assertEquals(Status.OK, response.getStatus());
			response.getData().values().forEach(entry -> assertEquals(ShipType.CRUISER, entry.getType()));
			pageNo++;
		} while (response.getMeta().getPage_total() >= pageNo);
	}

	@Test
	public void testShipRequestFilterClassCruisersAndBattleShips() {
		WarshipsRequest request = WarshipsRequest.createRequest().region(Region.EU).shipType(ShipType.CRUISER, ShipType.BATTLESHIP);
		Warships response;
		int pageNo = 1;
		do {
			response = request.pageNo(pageNo).fetch();
			assertNotNull(response);
			assertEquals(Status.OK, response.getStatus());
			response.getData().values().forEach(entry -> assertTrue(entry.getType().equals(ShipType.CRUISER) || entry.getType().equals(ShipType.BATTLESHIP)));
			pageNo++;
		} while (response.getMeta().getPage_total() >= pageNo);
	}

	@Test
	public void testShipRequestFilterTierX() {
		WarshipsRequest request = WarshipsRequest.createRequest().region(Region.EU).tier(Tier.X);
		Warships response;
		int pageNo = 1;
		do {
			response = request.pageNo(pageNo).fetch();
			assertNotNull(response);
			assertEquals(Status.OK, response.getStatus());
			response.getData().values().forEach(entry -> assertEquals(Tier.X, entry.getTier()));
			pageNo++;
		} while (response.getMeta().getPage_total() >= pageNo);
	}

	@Test
	public void testShipRequestFilterTierIAndII() {
		WarshipsRequest request = WarshipsRequest.createRequest().region(Region.EU).tier(Tier.I, Tier.II);
		Warships response;
		int pageNo = 1;
		do {
			response = request.pageNo(pageNo).fetch();
			assertNotNull(response);
			assertEquals(Status.OK, response.getStatus());
			response.getData().values().forEach(entry -> assertTrue(entry.getTier().equals(Tier.I) || entry.getTier().equals(Tier.II)));
			pageNo++;
		} while (response.getMeta().getPage_total() >= pageNo);
	}

	@Test
	public void testShipRequestFilterNationEurope() {
		WarshipsRequest request = WarshipsRequest.createRequest().region(Region.EU).nation(Nation.EUROPE);
		Warships response;
		int pageNo = 1;
		do {
			response = request.pageNo(pageNo).fetch();
			assertNotNull(response);
			assertEquals(Status.OK, response.getStatus());
			response.getData().values().forEach(entry -> assertEquals(Nation.EUROPE, entry.getNation()));
			pageNo++;
		} while (response.getMeta().getPage_total() >= pageNo);
	}

	@Test
	public void testShipRequestFilterNationEuropeAndPanAsia() {
		WarshipsRequest request = WarshipsRequest.createRequest().region(Region.EU).nation(Nation.EUROPE, Nation.PAN_ASIA);
		Warships response;
		int pageNo = 1;
		do {
			response = request.pageNo(pageNo).fetch();
			assertNotNull(response);
			assertEquals(Status.OK, response.getStatus());
			response.getData().values().forEach(entry -> assertTrue(entry.getNation().equals(Nation.EUROPE) || entry.getNation().equals(Nation.PAN_ASIA)));
			pageNo++;
		} while (response.getMeta().getPage_total() >= pageNo);
	}


	@Test
	public void testShipRequestFilterTypeResearch() {
		WarshipsRequest request = WarshipsRequest.createRequest().region(Region.EU).shipCategory(ShipCategory.RESEARCH);
		Warships response;
		int pageNo = 1;
		do {
			response = request.pageNo(pageNo).fetch();
			assertNotNull(response);
			assertEquals(Status.OK, response.getStatus());
			response.getData().values().forEach(entry -> assertTrue(!entry.is_premium() && !entry.is_special()));
			pageNo++;
		} while (response.getMeta().getPage_total() >= pageNo);
	}

	@Test
	public void testShipRequestFilterTypePremiumOrSpecial() {
		WarshipsRequest request = WarshipsRequest.createRequest().region(Region.EU).shipCategory(ShipCategory.PREMIUM, ShipCategory.SPECIAL);
		Warships response;
		int pageNo = 1;
		do {
			response = request.pageNo(pageNo).fetch();
			assertNotNull(response);
			assertEquals(Status.OK, response.getStatus());
			response.getData().values().forEach(entry -> assertTrue(entry.is_premium() || entry.is_special()));
			pageNo++;
		} while (response.getMeta().getPage_total() >= pageNo);
	}

	@Test
	public void testConsumableRequest() {
		ConsumablesRequest request = ConsumablesRequest.createRequest().region(Region.EU);
		Consumables response = request.fetch();
		assert response.getStatus().get() : response;
	}

	@Test
	public void testConsumableRequestFlags() {
		ConsumablesRequest request = ConsumablesRequest.createRequest().region(Region.EU).type(ConsumableType.FLAGS);
		Consumables response = request.fetch();
		assert response.getStatus().get() : response;
		for (Map.Entry<String, Consumables.Consumable> entry : response.getData().entrySet()) {
			assert entry.getValue().getType() == ConsumableType.FLAGS : entry.getValue();
			assert entry.getKey().equals(String.valueOf(entry.getValue().getConsumable_id())) : entry;
		}
	}
}
