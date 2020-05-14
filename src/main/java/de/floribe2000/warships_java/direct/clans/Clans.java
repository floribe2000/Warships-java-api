package de.floribe2000.warships_java.direct.clans;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;
import lombok.Getter;

import java.util.List;

@Getter
public class Clans implements IApiResponse {

    private Status status = Status.ERROR;

    /**
     * Details about errors in case of a failed request.
     * <p>Field is null if no errors occurred during the request!</p>
     */
    private ErrorContainer error = null;

    private Meta meta = null;

    private List<ClanDetails> data = null;

    @Getter
    public static class ClanDetails {

        private int members_count = 0;

        private long created_at = 0;

        private int clan_id = 0;

        private String tag = null;

        private String name = null;
    }

    @Override
    public String toString() {
        return IRequestAction.GSON.toJson(this);
    }
}
