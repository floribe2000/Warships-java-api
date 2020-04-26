package de.floribe2000.warships_java.direct.encyclopedia;

import de.floribe2000.warships_java.direct.api.AbstractRequest;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapsRequest extends AbstractRequest<MapsRequest, Maps> implements IRequestAction<Maps> {

    /**
     * The server region for this request
     */
    private Region region = null;

    /**
     * The language for the api response
     */
    private Language language = null;

    /**
     * Creates a new empty request of this class.
     *
     * @return an instance of this class
     */
    public static MapsRequest createRequest() {
        return new MapsRequest();
    }

    @Override
    public MapsRequest apiBuilder(String instanceName) {
        setInstance(instanceName);
        return this;
    }

    @Override
    public MapsRequest region(Region region) {
        this.region = region;
        return this;
    }

    @Override
    public MapsRequest language(Language language) {
        this.language = language;
        return this;
    }

    /**
     * Executes a request and returns the result of the request.
     * <p>All requests are executed synchronous on this thread. It is safe to execute this in a new thread if it is required to be run asynchronous.</p>
     *
     * @return an instance of {@link Maps} that contains all requested map data. If the request fails, an empty instance is returned.
     * @throws IllegalArgumentException If this method is called and region is null.
     */
    @Override
    protected Maps fetch(String url) {
        return connect(url, Maps.class, getLimiter());
    }

    @Override
    public String buildUrl() {
        checkRegion(region);
        String path = "/wows/encyclopedia/battlearenas/";
        return baseUrl(region, path, language, getInstanceName());
    }
}
