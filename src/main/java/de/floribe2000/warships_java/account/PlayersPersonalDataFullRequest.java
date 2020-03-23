package de.floribe2000.warships_java.account;

import de.floribe2000.warships_java.api.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class PlayersPersonalDataFullRequest implements RequestAction<PlayersPersonalDataFull>, AccountRequest {

    @NonNull
    private Region region;

    @NonNull
    private String accountId;

    private Set<ExtraField> extraFields = null;

    public PlayersPersonalDataFullRequest addExtraField(ExtraField... fields) {
        if (extraFields == null) {
            extraFields = new HashSet<>();
        }
        extraFields.addAll(Arrays.asList(fields));
        return this;
    }

    @Override
    public PlayersPersonalDataFull fetch() {
        String path = "/wows/account/info/";
        PlayersPersonalDataFull result;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(region.getBaseURL() + path + ApiBuilder.getApiKeyAsParam()
                + "&account_id=" + accountId + buildFieldString("extra", extraFields)).openStream()))) {
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
        PVP_DIV2("statistics.pvp_div2"),
        PVP_DIV3("statistics.pvp_div3"),
        PVE("statistics.pve"),
        PVE_DIV2("statistics.pve_div2"),
        PVE_DIV3("statistics.pve_div3"),
        RANK_SOLO("statistics.rank_solo"),
        RANK_DIV2("statistics.rank_div2"),
        RANK_DIV3("statistics.rank_div3");

        private String key;

        @Override
        public String retrieveKey() {
            return key;
        }
    }


}
