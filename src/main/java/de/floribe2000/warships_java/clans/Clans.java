package de.floribe2000.warships_java.clans;

import de.floribe2000.warships_java.api.IApiResponse;
import de.floribe2000.warships_java.common.Meta;
import lombok.Getter;

import java.util.List;

@Getter
public class Clans implements IApiResponse {

    private String status = "error";

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
}
