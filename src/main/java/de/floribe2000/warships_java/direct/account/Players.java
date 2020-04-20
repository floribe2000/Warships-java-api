package de.floribe2000.warships_java.direct.account;

import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.Status;
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
    private Status status = Status.ERROR;

    /**
     * The meta object of the api response
     */
    private Meta meta = null;

    /**
     * A list that contains all player details the api returned
     */
    private List<PlayerElement> data = null;

    /**
     * A class that represents a single player element from the api response.
     */
    @Getter
    public static class PlayerElement {
        /**
         * The nickname of the player
         */
        private String nickname = null;

        /**
         * The account id of the player
         */
        private int account_id = 0;
    }

    @Override
    public String toString() {
        return IRequestAction.GSON.toJson(this);
    }
}