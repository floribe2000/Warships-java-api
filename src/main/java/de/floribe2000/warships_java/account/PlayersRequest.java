package de.floribe2000.warships_java.account;

import de.floribe2000.warships_java.api.ApiBuilder;
import de.floribe2000.warships_java.api.Region;
import de.floribe2000.warships_java.api.RequestAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class PlayersRequest implements RequestAction<Players> {

    @NonNull
    private Region region;

    @NonNull
    private String searchText;

    private Set<ResponseField> fields = null;

    @NonNull
    public PlayersRequest setResponseField(ResponseField... fields) {
        if (this.fields == null) {
            this.fields = new HashSet<>();
        }
        this.fields.addAll(Arrays.asList(fields));
        return this;
    }

    //TODO
    @Override
    public Players fetch() {
        String path = "/wows/account/list/";
        StringBuilder fieldStr = new StringBuilder();
        if (fields != null && fields.size() > 0) {
            fieldStr.append("&fields=");
            String prefix = "";
            for (ResponseField field : fields) {
                fieldStr.append(prefix);
                fieldStr.append(field.getName());
                prefix = ",";
            }
        }
        String url = region.getBaseURL() + path + ApiBuilder.getApiKeyAsParam() + "&search=" + searchText + fieldStr.toString();
        Players result;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            result = GSON.fromJson(reader, Players.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Error while processing request");
        }
        return result;
    }

    @AllArgsConstructor
    public enum ResponseField {
        ACCOUNT_ID("account_id"),
        NICKNAME("nickname");

        @NonNull
        @Getter
        private String name;
    }

}
