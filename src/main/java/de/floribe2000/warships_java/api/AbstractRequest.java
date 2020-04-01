package de.floribe2000.warships_java.api;

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
}
