package de.floribe2000.warships_java.account;

import de.floribe2000.warships_java.api.ApiBuilder;
import de.floribe2000.warships_java.api.IResponseFields;
import de.floribe2000.warships_java.api.Region;
import de.floribe2000.warships_java.api.RequestAction;
import lombok.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Builder
public class PlayersRequest implements RequestAction<Players>, AccountRequest {

    @NonNull
    private Region region;

    @NonNull
    private String searchText;

    private Set<ResponseField> fields;

//    @NonNull
//    public PlayersRequest setResponseField(ResponseField... fields) {
//        if (this.fields == null) {
//            this.fields = new HashSet<>();
//        }
//        this.fields.addAll(Arrays.asList(fields));
//        return this;
//    }

    //TODO
    @Override
    public Players fetch() {
        String path = "/wows/account/list/";
        String url = region.getBaseURL() + path + ApiBuilder.getApiKeyAsParam() + "&search=" + searchText + buildFieldString("fields", fields);
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
    public enum ResponseField implements IResponseFields {
        ACCOUNT_ID("account_id"),
        NICKNAME("nickname");

        @NonNull
        private String key;

        @Override
        public String retrieveKey() {
            return key;
        }
    }

    public static class PlayersRequestBuilder {
        public PlayersRequestBuilder addField(ResponseField field) {
            if (fields == null) {
                fields = new HashSet<>();
            }
            fields.add(field);
            return this;
        }
    }

}
