package de.floribe2000.warships_java.fluent.api;

import de.floribe2000.warships_java.api.Region;

public class API {

	/**
	 * Non-static applicationId to support parallel requests using multiple applicationIds
	 */
	protected String applicationId;

	public API(String applicationId) {
		super();
		this.applicationId = applicationId;
	}

	public BaseRequest ofRegion(Region region) {
		return new BaseRequest(applicationId, region);
	}
}
