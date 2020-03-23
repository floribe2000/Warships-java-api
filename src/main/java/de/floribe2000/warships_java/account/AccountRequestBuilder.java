package de.floribe2000.warships_java.account;

import de.floribe2000.warships_java.api.Region;

import java.util.List;

public final class AccountRequestBuilder {

    public static PlayersRequest playersRequest(Region region, String searchText) {
        return new PlayersRequest(region, searchText);
    }

    public static PlayersPersonalDataFullRequest playersPersonalDataFullRequest(Region region, String accountId) {
        return new PlayersPersonalDataFullRequest(region, accountId);
    }

    public static PlayersAchievmentsRequest playersAchievmentsRequest(Region region, String accountId) {
        return new PlayersAchievmentsRequest(region, accountId);
    }

    public static PlayersAchievmentsRequest playersAchievmentsRequest(Region region, List<String> accountId) {
        return new PlayersAchievmentsRequest(region, accountId);
    }
}
