package de.floribe2000.warships_java.account;

import de.floribe2000.warships_java.api.*;
import lombok.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayersRequest implements RequestAction<Players>, AccountRequest {

    private Region region = null;

    private String searchText = null;

    private Set<ResponseField> fields = null;

//    @NonNull
//    public PlayersRequest setResponseField(ResponseField... fields) {
//        if (this.fields == null) {
//            this.fields = new HashSet<>();
//        }
//        this.fields.addAll(Arrays.asList(fields));
//        return this;
//    }

    public static PlayersRequest createRequest() {
        return new PlayersRequest();
    }

    public PlayersRequest region(Region region) {
        this.region = region;
        return this;
    }

    public PlayersRequest searchText(String text) {
        searchText = text;
        return this;
    }

    /**
     * Adds a field to the request while keeping all existing fields
     *
     * @param fields the fields to add
     * @return this instance
     */
    public PlayersRequest addFields(ResponseField... fields) {
        if (this.fields == null) {
            this.fields = new HashSet<>();
        }
        this.fields.addAll(Arrays.asList(fields));
        return this;
    }

    /**
     * Replaces all currently set fields with a new list of fields
     *
     * @param fields the new fields
     * @return this instance
     */
    public PlayersRequest fields(ResponseField... fields) {
        this.fields = new HashSet<>();
        this.fields.addAll(Arrays.asList(fields));
        return this;
    }

    //TODO
    @Override
    public Players fetch() {
        if (region == null || searchText == null) {
            throw new IllegalStateException("You can't use this method before setting all parameters");
        }
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

}
