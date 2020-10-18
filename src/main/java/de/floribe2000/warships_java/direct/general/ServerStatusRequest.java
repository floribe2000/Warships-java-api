package de.floribe2000.warships_java.direct.general;

import de.floribe2000.warships_java.direct.api.AbstractRequest;
import de.floribe2000.warships_java.direct.api.ApiBuilder;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region;

/**
 * A class to create a server status request.
 * Server status requests retrieve data about the current number of players on all available servers for a region.
 * <p>If you need data for multiple regions, call this request multiple times after changing the region.</p>
 *
 * @author floribe2000
 */
public class ServerStatusRequest extends AbstractRequest<ServerStatusRequest, ServerStatus> {

    /**
     * The server region for this request
     */
    private Region region = null;

    /**
     * The language for the api response
     */
    private Language language = null;

    public ServerStatusRequest() {
    }

    public static ServerStatusRequest createRequest() {
        return new ServerStatusRequest();
    }

    @Override
    protected ServerStatus fetch(String url) {
        return connect(url, ServerStatus.class, getLimiter());
    }

    @Override
    public ServerStatusRequest region(Region region) {
        this.region = region;
        return this;
    }

    @Override
    public ServerStatusRequest language(Language language) {
        this.language = language;
        return this;
    }

    @Override
    public ServerStatusRequest apiBuilder(String instanceName) {
        setInstance(instanceName);
        return this;
    }

    @Override
    public String buildUrl() {
        if (region == null) {
            throw new IllegalArgumentException("Region must not be null.");
        }
        String path = "/wgn/servers/info/";
        return baseUrl(region, path, language, getInstanceName());
    }

    @Override
    public String baseUrl(Region region, String path, Language language, String instanceName) {
        String url = region.getBaseURL() + path + ApiBuilder.getApiKeyAsParam(instanceName) + createLanguageField(language);
        return url.replace("api.worldofwarships.", "api.worldoftanks.");
    }
}
