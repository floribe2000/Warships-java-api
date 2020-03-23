package de.floribe2000.warships_java.fluent.api;

import de.floribe2000.warships_java.api.Region;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestExperimentalFluentAPI {

	private static final String APPLICATION_ID = "2a2db958e4a88d09e0de942d93fbb4d9";
	private static final long ACCOUNT_ID = 540241530L;
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

}
