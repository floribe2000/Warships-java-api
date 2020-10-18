package de.floribe2000.warships_java.direct.clans;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;

import java.util.List;
import java.util.Map;

public class ClanDetails implements IApiResponse {

    private Status status = Status.ERROR;

    /**
     * Details about errors in case of a failed request.
     * <p>Field is null if no errors occurred during the request!</p>
     */
    private ErrorContainer error = null;

    private Meta meta = null;

    private Map<String, ClanEntry> data = null;

    public Status getStatus() {
        return this.status;
    }

    public ErrorContainer getError() {
        return this.error;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public Map<String, ClanEntry> getData() {
        return this.data;
    }

    public static class ClanEntry {

        private int members_count = 0;

        private String name = null;

        private String creator_name = null;

        private long created_at = 0;

        private String tag = null;

        private long updated_at = 0;

        private String leader_name = null;

        private List<Long> members_ids = null;

        private long creator_id = 0;

        private long clan_id = 0;

        private Map<String, ClanMember> members = null;

        public int getMembers_count() {
            return this.members_count;
        }

        public String getName() {
            return this.name;
        }

        public String getCreator_name() {
            return this.creator_name;
        }

        public long getCreated_at() {
            return this.created_at;
        }

        public String getTag() {
            return this.tag;
        }

        public long getUpdated_at() {
            return this.updated_at;
        }

        public String getLeader_name() {
            return this.leader_name;
        }

        public List<Long> getMembers_ids() {
            return this.members_ids;
        }

        public long getCreator_id() {
            return this.creator_id;
        }

        public long getClan_id() {
            return this.clan_id;
        }

        public Map<String, ClanMember> getMembers() {
            return this.members;
        }

        public String getOld_name() {
            return this.old_name;
        }

        public boolean is_clan_disbanded() {
            return this.is_clan_disbanded;
        }

        public long getRenamed_at() {
            return this.renamed_at;
        }

        public String getOld_tag() {
            return this.old_tag;
        }

        public long getLeader_id() {
            return this.leader_id;
        }

        public String getDescription() {
            return this.description;
        }

        public static class ClanMember {

            private ClanRole role = null;

            private long joined_at = 0;

            private long account_id = 0;

            private String account_name = null;

            public ClanRole getRole() {
                return this.role;
            }

            public long getJoined_at() {
                return this.joined_at;
            }

            public long getAccount_id() {
                return this.account_id;
            }

            public String getAccount_name() {
                return this.account_name;
            }
        }

        private String old_name = null;

        private boolean is_clan_disbanded = false;

        private long renamed_at = 0;

        private String old_tag = null;

        private long leader_id = 0;

        private String description = null;
    }

    @Override
    public String toString() {
        return IRequestAction.GSON.toJson(this);
    }
}
