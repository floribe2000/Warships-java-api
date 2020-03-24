package de.floribe2000.warships_java.account;

import de.floribe2000.warships_java.api.IApiResponse;
import de.floribe2000.warships_java.common.Meta;
import lombok.Getter;

import java.util.Map;

@Getter
public class PlayersStatisticsByDate implements IApiResponse {

    private String status = "error";

    private Meta meta = null;

    private Map<String, PlayerEntry> data = null;

    @Getter
    public static class PlayerEntry {

        private Map<String, Day> pvp = null;

        private Map<String, Day> pve = null;

        @Getter
        public static class Day {

            private int capture_points = 0;

            private int account_id = 0;

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
        }
    }
}
