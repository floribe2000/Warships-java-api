package de.floribe2000.warships_java.direct.encyclopedia;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.typeDefinitions.*;

import java.util.List;
import java.util.Map;

public class Warships implements IApiResponse {

    private Status status = Status.ERROR;

    /**
     * Details about errors in case of a failed request.
     * <p>Field is null if no errors occurred during the request!</p>
     */
    private ErrorContainer error = null;

    private Meta meta = null;

    private Map<Long, ShipEntry> data = null;

    public Status getStatus() {
        return this.status;
    }

    public ErrorContainer getError() {
        return this.error;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public Map<Long, ShipEntry> getData() {
        return this.data;
    }

    public static class ShipEntry {

        private String description = null;

        private int price_gold = 0;

        private String ship_id_str = null;

        private boolean has_demo_profile = false;

        private ImageDetails images = null;

        public String getDescription() {
            return this.description;
        }

        public int getPrice_gold() {
            return this.price_gold;
        }

        public String getShip_id_str() {
            return this.ship_id_str;
        }

        public boolean isHas_demo_profile() {
            return this.has_demo_profile;
        }

        public ImageDetails getImages() {
            return this.images;
        }

        public ShipModules getModules() {
            return this.modules;
        }

        public Map<String, ModuleDetails> getModules_tree() {
            return this.modules_tree;
        }

        public Nation getNation() {
            return this.nation;
        }

        public boolean is_premium() {
            return this.is_premium;
        }

        public long getShip_id() {
            return this.ship_id;
        }

        public int getPrice_credit() {
            return this.price_credit;
        }

        public List<Long> getUpgrades() {
            return this.upgrades;
        }

        public Tier getTier() {
            return this.tier;
        }

        public Map<String, Integer> getNext_ships() {
            return this.next_ships;
        }

        public int getMod_slots() {
            return this.mod_slots;
        }

        public ShipType getType() {
            return this.type;
        }

        public boolean is_special() {
            return this.is_special;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static class ImageDetails {

            private String small = null;

            private String large = null;

            private String medium = null;

            private String contour = null;

            public String getSmall() {
                return this.small;
            }

            public String getLarge() {
                return this.large;
            }

            public String getMedium() {
                return this.medium;
            }

            public String getContour() {
                return this.contour;
            }
        }

        private ShipModules modules = null;

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

            public List<Long> getEngine() {
                return this.engine;
            }

            public List<Long> getTorpedo_bomber() {
                return this.torpedo_bomber;
            }

            public List<Long> getFighter() {
                return this.fighter;
            }

            public List<Long> getHull() {
                return this.hull;
            }

            public List<Long> getArtillery() {
                return this.artillery;
            }

            public List<Long> getTorpedoes() {
                return this.torpedoes;
            }

            public List<Long> getFire_control() {
                return this.fire_control;
            }

            public List<Long> getFlight_control() {
                return this.flight_control;
            }

            public List<Long> getDive_bomber() {
                return this.dive_bomber;
            }
        }

        private Map<String, ModuleDetails> modules_tree = null;

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

            public String getName() {
                return this.name;
            }

            public List<Long> getNext_modules() {
                return this.next_modules;
            }

            public boolean is_default() {
                return this.is_default;
            }

            public int getPrice_xp() {
                return this.price_xp;
            }

            public int getPrice_credit() {
                return this.price_credit;
            }

            public List<Long> getNext_ships() {
                return this.next_ships;
            }

            public long getModule_id() {
                return this.module_id;
            }

            public ModuleType getType() {
                return this.type;
            }

            public String getModule_id_str() {
                return this.module_id_str;
            }
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

        private String name = null;

        public ShipEntryReduced getReducedView() {
            return new ShipEntryReduced(this);
        }

        @Override
        public String toString() {
            return IRequestAction.Companion.getGSON().toJson(this);
        }
    }

    @Override
    public String toString() {
        return IRequestAction.Companion.getGSON().toJson(this);
    }
}
