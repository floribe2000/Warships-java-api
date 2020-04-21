package de.floribe2000.warships_java.direct.api;

import de.floribe2000.warships_java.direct.api.typeDefinitions.Region;
import de.floribe2000.warships_java.requests.SimpleRateLimiter;

public abstract class AbstractRequest<R> implements IRequest<R> {

    private ApiBuilder instance = null;

    protected void setInstance(String instanceName) {
        instance = ApiBuilder.getInstanceWithName(instanceName);
    }

    abstract public R apiBuilder(String instanceName);

    protected final SimpleRateLimiter getLimiter() {
        if (instance == null) {
            return ApiBuilder.getInstanceWithName(null).getRateLimiter();
        } else {
            return instance.getRateLimiter();
        }
    }

    protected final String getInstanceName() {
        if (instance != null) {
            return instance.getInstanceName();
        } else {
            return null;
        }
    }

    /**
     * A utility method to make it easier to check the region.
     *
     * @param region the region to check
     * @throws IllegalArgumentException Thrown if the region is null.
     */
    protected void checkRegion(Region region) {
        if (region == null) {
            throw new IllegalArgumentException("Region must not be null.");
        }
    }
}
