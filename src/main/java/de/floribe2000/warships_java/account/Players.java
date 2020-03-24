package de.floribe2000.warships_java.account;

import de.floribe2000.warships_java.api.IApiResponse;
import de.floribe2000.warships_java.api.IRequestAction;
import de.floribe2000.warships_java.common.Meta;
import lombok.Getter;

import java.util.List;

/**
 * A representation of the results of the player search result from <a href="https://api.worldofwarships.eu/wows/account/list/">/wows/account/list/</a>.
 * This class holds all data returned from this request to allow easy processing of the information.
 *
 * @author floribe2000
 */
@Getter
public class Players implements IApiResponse {

    /**
     * The response status from the api
     */
    private String status;

    /**
     * The meta object of the api response
     */
    private Meta meta;

    /**
     * A list that contains all player details the api returned
     */
    private List<PlayerElement> data;

    /**
     * A class that represents a single player element from the api response.
     */
    @Getter
    public static class PlayerElement {
        /**
         * The nickname of the player
         */
        private String nickname;

        /**
         * The account id of the player
         */
        private int account_id;
    }

    @Override
    public String toString() {
        return IRequestAction.GSON.toJson(this);
    }
}