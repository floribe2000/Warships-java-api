package de.floribe2000.warships_java.account;

import de.floribe2000.warships_java.api.ApiBuilder;
import de.floribe2000.warships_java.api.Region;
import de.floribe2000.warships_java.api.RequestAction;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class PlayersPersonalDataFullRequest implements RequestAction<PlayersPersonalDataFull> {

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
        StringBuilder url = new StringBuilder(region.getBaseURL() + path + ApiBuilder.getApiKeyAsParam() + "&account_id=" + accountId);
        if (extraFields != null && extraFields.size() > 0) {
            url.append("&extra=");
            String prefix = "";
            for (ExtraField field : extraFields) {
                url.append(prefix);
                url.append(field.key);
                prefix = ",";
            }
        }
        PlayersPersonalDataFull result;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url.toString()).openStream()))) {
            result = GSON.fromJson(reader, PlayersPersonalDataFull.class);
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
            throw new IllegalStateException("Error while processing request");
        }
        return result;
    }

    @AllArgsConstructor
    public enum ExtraField {
        PVP_DIV2("statistics.pvp_div2"),
        PVP_DIV3("statistics.pvp_div3"),
        PVE("statistics.pve"),
        PVE_DIV2("statistics.pve_div2"),
        PVE_DIV3("statistics.pve_div3"),
        RANK_SOLO("statistics.rank_solo"),
        RANK_DIV2("statistics.rank_div2"),
        RANK_DIV3("statistics.rank_div3");

        private String key;
    }


}
