package de.floribe2000.warships_java.account;

import de.floribe2000.warships_java.api.IApiResponse;
import de.floribe2000.warships_java.api.IRequestAction;
import de.floribe2000.warships_java.common.Meta;
import lombok.Getter;

import java.util.Map;

/**
 * A representation of a full data set from the api.
 * Contains all available fields for player and player pvp stats.
 */
@Getter
public class PlayersPersonalDataFull implements IApiResponse {

    /**
     * The response status from the api
     */
    private String status = "error";

    /**
     * The meta object of the api response
     */
    private Meta meta = null;

    private Map<String, PlayerDetails> data = null;

    /**
     * A representation of individual player statistics.
     */
    @Getter
    public static class PlayerDetails {

        private int last_battle_time = 0;

        private int account_id = 0;

        private int leveling_tier = 0;

        private int created_at = 0;

        private boolean hidden_profile = false;

        private int logout_at = 0;

        private Statistics statistics = null;

        /**
         * A representation of the player's battle statistics
         */
        @Getter
        public static class Statistics {

            private int distance = 0;

            private int battles = 0;

            private ModeStats pvp = null;

            private ModeStats pve = null;

            private ModeStats pvp_div2 = null;

            private ModeStats pvp_div3 = null;

            private ModeStats pve_div2 = null;

            private ModeStats pve_div3 = null;

            private ModeStats rank_solo = null;

            private ModeStats rank_div2 = null;

            private ModeStats rank_div3 = null;

            /**
             * The player's pvp statistics
             */
            @Getter
            public static class ModeStats {

                private int max_xp = 0;

                private int damage_to_buildings = 0;

                private MainBattery main_battery = null;

                @Getter
                public static class MainBattery {
                    private int max_frags_battle = 0;

                    private int frags = 0;

                    private int hits = 0;

                    private long max_frags_ship_id = 0;

                    private int shots = 0;
                }

                private long max_ships_spotted_ship_id = 0;

                private int max_damage_scouting = 0;

                private long art_agro = 0;

                private long max_xp_ship_id = 0;

                private int ships_spotted = 0;

                private SecondaryBattery secondary_battery = null;

                @Getter
                public static class SecondaryBattery {
                    private int max_frags_battle = 0;

                    private int frags = 0;

                    private int hits = 0;

                    private long max_frags_ship_id = 0;

                    private int shots = 0;
                }

                private long max_frags_ship_id = 0;

                private int xp = 0;

                private int survived_battles = 0;

                private int dropped_capture_points = 0;

                private int max_damage_dealt_to_buildings = 0;

                private int torpedo_agro = 0;

                private int draws = 0;

                private int control_captured_points = 0;

                private int battles_since_510 = 0;

                private long max_total_agro_ship_id = 0;

                private int planes_killed = 0;

                private int battles = 0;

                private int max_ships_spotted = 0;

                private long max_suppressions_ship_id = 0;

                private int survived_wins = 0;

                private int frags = 0;

                private int damage_scouting = 0;

                private int max_total_agro = 0;

                private int max_frags_battle = 0;

                private int capture_points = 0;

                private Ramming ramming = null;

                @Getter
                public static class Ramming {

                    private int max_frags_battle = 0;

                    private int frags = 0;

                    private long max_frags_ship_id = 0;
                }

                private int suppressions_count = 0;

                private int max_suppressions_count = 0;

                private Torpedoes torpedoes = null;

                @Getter
                public static class Torpedoes {
                    private int max_frags_battle = 0;

                    private int frags = 0;

                    private int hits = 0;

                    private long max_frags_ship_id = 0;

                    private int shots = 0;
                }

                private long max_planes_killed_ship_id = 0;

                private Aircraft aircraft = null;

                @Getter
                public static class Aircraft {

                    private int max_frags_battle = 0;

                    private int frags = 0;

                    private long max_frags_ship_id = 0;
                }

                private int team_capture_points = 0;

                private int control_dropped_points = 0;

                private int max_damage_dealt = 0;

                private long max_damage_dealt_to_buildings_ship_id = 0;

                private long max_damage_dealt_ship_id = 0;

                private int wins = 0;

                private int losses = 0;

                private int damage_dealt = 0;

                private int max_planes_killed = 0;

                private long max_scouting_damage_ship_id = 0;

                private int team_dropped_capture_points = 0;

                private int battles_since_512 = 0;
            }
        }

        private String nickname = null;

        private int stats_updated_at = 0;
    }

    @Override
    public String toString() {
        return IRequestAction.GSON.toJson(this);
    }
}
