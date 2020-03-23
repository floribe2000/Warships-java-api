package de.floribe2000.warships_java.account;

import de.floribe2000.warships_java.api.Region;

public final class AccountRequestBuilder {

    public static PlayersRequest playersRequest(Region region, String searchText) {
        return new PlayersRequest(region, searchText);
    }

    public static PlayersPersonalDataFullRequest playersPersonalDataFullRequest(Region region, String accountId) {
        return new PlayersPersonalDataFullRequest(region, accountId);
    }
}
