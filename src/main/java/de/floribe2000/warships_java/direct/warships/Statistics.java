package de.floribe2000.warships_java.direct.warships;

import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class Statistics implements IApiResponse {

    private String status = "error";

    private Meta meta = null;

    private Map<String, List<ShipEntry>> data = null;

    @Getter
    public static class ShipEntry {

        private ModeStats pvp = null;

        private ModeStats pve = null;

        private ModeStats pve_div2 = null;

        private ModeStats pve_div3 = null;

        private ModeStats pve_solo = null;

        private ModeStats pvp_div2 = null;

        private ModeStats pvp_div3 = null;

        private ModeStats pvp_solo = null;

        private ModeStats rank_div2 = null;

        private ModeStats rank_div3 = null;

        private ModeStats rank_solo = null;

        private OperationMode oper_div = null;

        private OperationMode oper_div_hard = null;

        private OperationMode oper_solo = null;

        @Getter
        public static class ModeStats {

            private int max_xp = 0;

            private int damage_to_buildings = 0;

            private WeaponType main_battery = null;

            private int suppressions_count = 0;

            private int max_damage_scouting = 0;

            private int art_agro = 0;

            private int ships_spotted = 0;

            private WeaponType second_battery = null;

            private int xp = 0;

            private int survived_battles = 0;

            private int dropped_capture_points = 0;

            protected int max_damage_dealt_to_buildings = 0;

            private int torpedo_agro = 0;

            private int draws = 0;

            private int battles_since_510 = 0;

            private int planes_killed = 0;

            private int battles = 0;

            private int max_ships_Spotted = 0;

            private int team_capture_points = 0;

            private int frags = 0;

            private int damage_scouting = 0;

            private int max_total_agro = 0;

            private int max_frags_battle = 0;

            private int capture_points = 0;

            private WeaponTypeReduced ramming = null;

            private WeaponType torpedoes = null;

            private WeaponTypeReduced aircraft = null;

            private int survived_wins = 0;

            private int max_damage_dealt = 0;

            private int wins = 0;

            private int losses = 0;

            private int damage_dealt = 0;

            private int max_planes_killed = 0;

            private int max_suppressions_count = 0;

            private int team_dropped_capture_points = 0;

            private int battles_since_512 = 0;

            @Getter
            public static class WeaponTypeReduced {

                private int max_frags_battle = 0;

                private int frags = 0;
            }

            @Getter
            public static class WeaponType {

                private int max_frags_battle = 0;

                private int frags = 0;

                private int hits = 0;

                private int shots = 0;
            }

        }

        @Getter
        public static class OperationMode {

            private int wins = 0;

            private int losses = 0;

            private int battles = 0;

            private int survived_wins = 0;

            private int xp = 0;

            private Map<String, Integer> wins_by_tasks = null;

            private int survived_battles = 0;
        }

        private long last_battle_time = 0;

        private int account_id = 0;

        private int distance = 0;

        private long updated_at = 0;

        private int battles = 0;

        private long ship_id = 0;

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
