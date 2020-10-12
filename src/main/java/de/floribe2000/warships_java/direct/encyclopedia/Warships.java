package de.floribe2000.warships_java.direct.encyclopedia;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.typeDefinitions.ModuleType;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Nation;
import de.floribe2000.warships_java.direct.api.typeDefinitions.ShipType;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Tier;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Warships implements IApiResponse {

    private Status status = Status.ERROR;

    /**
     * Details about errors in case of a failed request.
     * <p>Field is null if no errors occurred during the request!</p>
     */
    private ErrorContainer error = null;

    private Meta meta = null;

    private Map<Long, ShipEntry> data = null;

    @Getter
    public static class ShipEntry {

        private String description = null;

        private int price_gold = 0;

        private String ship_id_str = null;

        private boolean has_demo_profile = false;

        private ImageDetails images = null;

        @Getter
        public static class ImageDetails {

            private String small = null;

            private String large = null;

            private String medium = null;

            private String contour = null;
        }

        private ShipModules modules = null;

        @Getter
        public static class ShipModules {

            private List<Long> engine = null;

            private List<Long> torpedo_bomber = null;

            private List<Long> fighter = null;

            private List<Long> hull = null;

            private List<Long> artillery = null;

            private List<Long> torpedoes = null;

            private List<Long> fire_control = null;

            private List<Long> flight_control = null;

            private List<Long> dive_bomber = null;
        }

        private Map<String, ModuleDetails> modules_tree = null;

        @Getter
        public static class ModuleDetails {

            private String name = null;

            private List<Long> next_modules = null;

            private boolean is_default = false;

            private int price_xp = 0;

            private int price_credit = 0;

            private List<Long> next_ships = null;

            private long module_id = 0;

            private ModuleType type = null;

            private String module_id_str = null;
        }

        private Nation nation = null;

        private boolean is_premium = false;

        private long ship_id = 0;

        private int price_credit = 0;

        //TODO default profile configuration

        private List<Long> upgrades = null;

        private Tier tier = null;

        private Map<String, Integer> next_ships = null;

        private int mod_slots = 5;

        private ShipType type = null;

        private boolean is_special = false;

        @Setter
        private String name = null;

        public ShipEntryReduced getReducedView() {
            return new ShipEntryReduced(this);
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
