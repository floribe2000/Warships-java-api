package de.floribe2000.warships_java.direct.account;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;
import lombok.Getter;

import java.util.Map;

/**
 * A representation of the data retrieved from the api.
 * <p>Contains data of a player, split into up to 10 dates</p>
 *
 * @author floribe2000
 */
@Getter
public class PlayersStatisticsByDate implements IApiResponse {

    /**
     * The api response status
     */
    private Status status = Status.ERROR;

    /**
     * Details about errors in case of a failed request.
     * <p>Field is null if no errors occurred during the request!</p>
     */
    private ErrorContainer error = null;

    /**
     * The meta object of this api response
     */
    private Meta meta = null;

    /**
     * A map containing details for the player
     */
    private Map<String, PlayerEntry> data = null;

    /**
     * A representation of a single player entry
     */
    @Getter
    public static class PlayerEntry {

        /**
         * A Day object containing stats for the random battles mode
         */
        private Map<String, Day> pvp = null;

        /**
         * A Day object containing stats for the coop battles mode
         */
        private Map<String, Day> pve = null;

        /**
         * A representation of the stats for a requested day
         */
        @Getter
        public static class Day {

            private int capture_points = 0;

            private long account_id = 0;

            private int max_xp = 0;

            private int wins = 0;

            private int planes_killed = 0;

            private int battles = 0;

            private int damage_dealt = 0;

            private String battle_type = null;

            private String date = null;

            private int xp = 0;

            private int frags = 0;

            private int survived_battles = 0;

            private int dropped_capture_points = 0;

            @Override
            public String toString() {
                return IRequestAction.GSON.toJson(this);
            }
        }

        @Override
        public String toString() {
            return IRequestAction.GSON.toJson(this);
        }
    }

    @Override
    public String toString() {
        return IRequestAction.GSON.toJson(this);
    }
}
