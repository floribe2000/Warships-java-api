package de.floribe2000.warships_java.fluent.api;

import de.floribe2000.warships_java.api.Region;

public class BaseRequest implements Request {

	String applicationId;
	Region region;

	BaseRequest(String applicationId, Region region) {
		super();
		this.applicationId = applicationId;
		this.region = region;
	}

	/**
	 * Offers access to player specific info like players personal data and players ships stats
	 *
	 * @param accountId
	 * @return PlayerRequest
	 */
	public PlayerRequest ofPlayer(long accountId) {
		return new PlayerRequest(this, accountId);
	}
}
