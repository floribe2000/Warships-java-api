package de.floribe2000.warships_java.account;

import de.floribe2000.warships_java.api.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class PlayersPersonalDataFullRequest implements RequestAction<PlayersPersonalDataFull>, AccountRequest {

    @NonNull
    private Region region;

    @NonNull
    private Set<String> accountId;

    private Set<ExtraField> extraFields = null;

    PlayersPersonalDataFullRequest(Region region, Set<String> accountId) {
        this.region = region;
        if (accountId.size() <= 100) {
            this.accountId = accountId;
        } else {
            throw new IllegalArgumentException("The list of account ids must not contain more than 100 IDs");
        }
    }

    PlayersPersonalDataFullRequest(Region region, String accountId) {
        this.region = region;
        this.accountId = new HashSet<>();
        this.accountId.add(accountId);
    }

    public PlayersPersonalDataFullRequest addExtraField(ExtraField... fields) {
        if (extraFields == null) {
            extraFields = new HashSet<>();
        }
        extraFields.addAll(Arrays.asList(fields));
        return this;
    }

    public PlayersPersonalDataFullRequest addAccountId(String accountId) {
        if (this.accountId.size() < 100) {
            this.accountId.add(accountId);
        } else {
            //TODO Logging
        }
        return this;
    }

    @Override
    public PlayersPersonalDataFull fetch() {
        String path = "/wows/account/info/";
        StringBuilder accounts = new StringBuilder();
        String prefix = "";
        for (String str : accountId) {
            accounts.append(prefix).append(str);
            prefix = ",";
        }
        PlayersPersonalDataFull result;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(region.getBaseURL() + path + ApiBuilder.getApiKeyAsParam()
                + "&account_id=" + accounts.toString() + buildFieldString("extra", extraFields)).openStream()))) {
            result = GSON.fromJson(reader, PlayersPersonalDataFull.class);
        } catch (Exception e) {
            //TODO exception handling
            e.printStackTrace();
            throw new IllegalStateException("Error while processing request");
        }
        return result;
    }

    @AllArgsConstructor
    public enum ExtraField implements IResponseFields {
    public enum ExtraField {
        PVP("statistics.pvp"), // as standard in each stats related request, unless you exclude it via "&fields=-statistics.pvp"
        PVP_SOLO("statistics.pvp_solo"),
        PVP_DIV2("statistics.pvp_div2"),
        PVP_DIV3("statistics.pvp_div3"),
        PVE("statistics.pve"),
        PVE_SOLO("statistics.pve_solo"),
        PVE_DIV2("statistics.pve_div2"),
        PVE_DIV3("statistics.pve_div3"),
        RANK_SOLO("statistics.rank_solo"),
        RANK_DIV2("statistics.rank_div2"),
        RANK_DIV3("statistics.rank_div3"),
        OPER_SOLO("statistics.oper_solo"),
        OPER_DIV("statistics.oper_div"),
        OPER_DIV_HARD("statistics.oper_div_hard");

        private String key;

        @Override
        public String retrieveKey() {
            return key;
        }
    }


}
