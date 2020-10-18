package de.floribe2000.warships_java.direct.account;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;

import java.util.Map;

/**
 * A representation of the data retrieved from the api.
 * <p>Contains data of a player, split into up to 10 dates</p>
 *
 * @author floribe2000
 */
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

    public Status getStatus() {
        return this.status;
    }

    public ErrorContainer getError() {
        return this.error;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public Map<String, PlayerEntry> getData() {
        return this.data;
    }

    /**
     * A representation of a single player entry
     */
    public static class PlayerEntry {

        /**
         * A Day object containing stats for the random battles mode
         */
        private Map<String, Day> pvp = null;

        /**
         * A Day object containing stats for the coop battles mode
         */
        private Map<String, Day> pve = null;

        public Map<String, Day> getPvp() {
            return this.pvp;
        }

        public Map<String, Day> getPve() {
            return this.pve;
        }

        /**
         * A representation of the stats for a requested day
         */
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

            public int getCapture_points() {
                return this.capture_points;
            }

            public long getAccount_id() {
                return this.account_id;
            }

            public int getMax_xp() {
                return this.max_xp;
            }

            public int getWins() {
                return this.wins;
            }

            public int getPlanes_killed() {
                return this.planes_killed;
            }

            public int getBattles() {
                return this.battles;
            }

            public int getDamage_dealt() {
                return this.damage_dealt;
            }

            public String getBattle_type() {
                return this.battle_type;
            }

            public String getDate() {
                return this.date;
            }

            public int getXp() {
                return this.xp;
            }

            public int getFrags() {
                return this.frags;
            }

            public int getSurvived_battles() {
                return this.survived_battles;
            }

            public int getDropped_capture_points() {
                return this.dropped_capture_points;
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
