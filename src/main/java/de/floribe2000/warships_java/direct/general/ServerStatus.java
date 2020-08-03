package de.floribe2000.warships_java.direct.general;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * The api response for a server status request.
 *
 * @author floribe2000
 */
@Getter
public class ServerStatus implements IApiResponse {

    private Status status = Status.ERROR;

    /**
     * Details about errors in case of a failed request.
     * <p>Field is null if no errors occurred during the request!</p>
     */
    private ErrorContainer error = null;

    /**
     * A list containing the details for all available servers.
     * <p>Possible entries are wot, wows and wotb</p>
     */
    private Map<String, List<ServerEntry>> data = null;

    @Getter
    public static class ServerEntry {

        private int players_online = 0;

        private String server = null;
    }

    @Override
    public String toString() {
        return IRequestAction.GSON.toJson(this);
    }
}
