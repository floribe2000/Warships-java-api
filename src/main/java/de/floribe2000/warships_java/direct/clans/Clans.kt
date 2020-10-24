package de.floribe2000.warships_java.direct.clans;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;

import java.util.List;

public class Clans implements IApiResponse {

    private Status status = Status.ERROR;

    /**
     * Details about errors in case of a failed request.
     * <p>Field is null if no errors occurred during the request!</p>
     */
    private ErrorContainer error = null;

    private Meta meta = null;

    private List<ClanDetails> data = null;

    public Status getStatus() {
        return this.status;
    }

    public ErrorContainer getError() {
        return this.error;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public List<ClanDetails> getData() {
        return this.data;
    }

    public static class ClanDetails {

        private int members_count = 0;

        private long created_at = 0;

        private int clan_id = 0;

        private String tag = null;

        private String name = null;

        public int getMembers_count() {
            return this.members_count;
        }

        public long getCreated_at() {
            return this.created_at;
        }

        public int getClan_id() {
            return this.clan_id;
        }

        public String getTag() {
            return this.tag;
        }

        public String getName() {
            return this.name;
        }
    }

    @Override
    public String toString() {
        return IRequestAction.Companion.getGSON().toJson(this);
    }
}
