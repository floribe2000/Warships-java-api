package de.floribe2000.warships_java.fluent.api;

import de.floribe2000.warships_java.api.Region;

public class BaseRequest implements Request {

	protected String applicationId;
	protected Region region;

	protected BaseRequest(String applicationId, Region region) {
		super();
		this.applicationId = applicationId;
		this.region = region;
	}

	public PlayerRequest ofPlayer(long accountId) {
		return new PlayerRequest(this, accountId);
	}
}
