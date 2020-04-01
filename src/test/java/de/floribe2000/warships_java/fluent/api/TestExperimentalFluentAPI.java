package de.floribe2000.warships_java.fluent.api;

import de.floribe2000.warships_java.api.Region;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

//TODO reactivate tests
@Ignore
public class TestExperimentalFluentAPI {

	private static final String APPLICATION_ID = "USEYOUROWNID";
	private static final long ACCOUNT_ID = 540241530L;
	private static final long OTHER_ACCOUNT_ID = 537376379L;
	private static final long SHIP_ID = 3762239184L;
	private static API api;

	@BeforeClass
	public static void prepare() {
		System.out.println("Preparing...");
		api = new API(APPLICATION_ID);
	}

	@Test
	public void testPlayersStats() throws IOException {
		System.out.println("Testing default players stats");
		BaseRequest eu = api.ofRegion(Region.EU);
		String request = eu.ofPlayer(ACCOUNT_ID).query();
		System.out.println(request);
		InputStream input = new URL(request).openStream();
		int i;
		while ((i = input.read()) != -1) {
			System.out.print((char) i);
		}
		System.out.println();
	}

	@Test
	public void testPlayersCoopStats() throws IOException {
		System.out.println("Testing players coop stats");
		BaseRequest eu = api.ofRegion(Region.EU);
		String request = eu.ofPlayer(ACCOUNT_ID).ofGameMode(GameMode.COOP).query();
		System.out.println(request);
		InputStream input = new URL(request).openStream();
		int i;
		while ((i = input.read()) != -1) {
			System.out.print((char) i);
		}
		System.out.println();
	}

	@Test
	public void testPlayersCombinedStats() throws IOException {
		System.out.println("Testing players random and coop stats");
		BaseRequest eu = api.ofRegion(Region.EU);
		String request = eu.ofPlayer(ACCOUNT_ID).ofGameModes(GameMode.COOP, GameMode.RANDOM).query();
		System.out.println(request);
		InputStream input = new URL(request).openStream();
		int i;
		while ((i = input.read()) != -1) {
			System.out.print((char) i);
		}
		System.out.println();
	}

	@Test
	public void testShipsStats() throws IOException {
		System.out.println("Testing ships stats");
		BaseRequest eu = api.ofRegion(Region.EU);
		PlayerRequest playerStats = eu.ofPlayer(ACCOUNT_ID).ofGameModes(GameMode.RANDOM);
		String playerRequest = playerStats.query();
		System.out.println(playerRequest);
		InputStream input = new URL(playerRequest).openStream();
		int i;
		while ((i = input.read()) != -1) {
			System.out.print((char) i);
		}
		System.out.println();

		PlayersShipsRequest playersShipsStats = playerStats.ofShip(SHIP_ID);
		String playersShipsRequest = playersShipsStats.query();
		System.out.println(playersShipsRequest);
		input = new URL(playersShipsRequest).openStream();
		while ((i = input.read()) != -1) {
			System.out.print((char) i);
		}
		System.out.println();

		playersShipsStats = playersShipsStats.clearGameModes().ofGameMode(GameMode.COOP);
		playersShipsRequest = playersShipsStats.query();
		System.out.println(playersShipsRequest);
		input = new URL(playersShipsRequest).openStream();
		while ((i = input.read()) != -1) {
			System.out.print((char) i);
		}
		System.out.println();
	}

	@Test
	public void testSwappingAccountId() throws IOException {
		System.out.println("Testing swapping accountId");
		PlayerRequest stats = api.ofRegion(Region.EU).ofPlayer(ACCOUNT_ID);
		String request = stats.query();
		System.out.println(request);
		InputStream input = new URL(request).openStream();
		int i;
		while ((i = input.read()) != -1) {
			System.out.print((char) i);
		}
		System.out.println();

		stats = stats.ofPlayer(OTHER_ACCOUNT_ID);
		request = stats.query();
		System.out.println(request);
		input = new URL(request).openStream();
		while ((i = input.read()) != -1) {
			System.out.print((char) i);
		}
		System.out.println();
	}

}
