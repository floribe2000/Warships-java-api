package de.floribe2000.warships_java.direct.clans;

import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.Status;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class ClanDetails implements IApiResponse {

    private Status status = Status.ERROR;

    private Meta meta = null;

    private Map<String, ClanEntry> data = null;

    @Getter
    public static class ClanEntry {

        private int members_count = 0;

        private String name = null;

        private String creator_name = null;

        private long created_at = 0;

        private String tag = null;

        private long updated_at = 0;

        private String leader_name = null;

        private List<Integer> members_ids = null;

        private int creator_id = 0;

        private int clan_id = 0;

        private Map<String, ClanMember> members = null;

        @Getter
        public static class ClanMember {

            private ClanRole role = null;

            private long joined_at = 0;

            private int account_id = 0;

            private String account_name = null;
        }

        private String old_name = null;

        private boolean is_clan_disbanded = false;

        private long renamed_at = 0;

        private String old_tag = null;

        private int leader_id = 0;

        private String description = null;
    }

    @Override
    public String toString() {
        return IRequestAction.GSON.toJson(this);
    }
}
