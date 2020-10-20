package de.floribe2000.warships_java.direct.encyclopedia;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.typeDefinitions.ShellType;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;

import java.util.Map;

/**
 * A representation of an api response for a {@link ShipParametersRequest}.
 * Contains data for a single ship.
 *
 * @author floribe2000
 */
public class ShipParameters implements IApiResponse {

    private Status status = Status.ERROR;

    private ErrorContainer error = null;

    private Meta meta = null;

    private Map<String, ShipParamDetails> data = null;

    public Status getStatus() {
        return this.status;
    }

    public ErrorContainer getError() {
        return this.error;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public Map<String, ShipParamDetails> getData() {
        return this.data;
    }

    public static class ShipParamDetails {

        private Engine engine = null;

        public Engine getEngine() {
            return this.engine;
        }

        public TorpedoBomber getTorpedo_bomber() {
            return this.torpedo_bomber;
        }

        public AntiAircraft getAnti_aircraft() {
            return this.anti_aircraft;
        }

        public Mobility getMobility() {
            return this.mobility;
        }

        public Hull getHull() {
            return this.hull;
        }

        public Atbas getAtbas() {
            return this.atbas;
        }

        public Artillery getArtillery() {
            return this.artillery;
        }

        public Torpedoes getTorpedoes() {
            return this.torpedoes;
        }

        public Fighters getFighters() {
            return this.fighters;
        }

        public long getShip_id() {
            return this.ship_id;
        }

        public FireControl getFire_control() {
            return this.fire_control;
        }

        public Weaponry getWeaponry() {
            return this.weaponry;
        }

        public int getBattle_level_range_max() {
            return this.battle_level_range_max;
        }

        public int getBattle_level_range_min() {
            return this.battle_level_range_min;
        }

        public FlightControl getFlight_control() {
            return this.flight_control;
        }

        public Concealment getConcealment() {
            return this.concealment;
        }

        public DiveBomber getDive_bomber() {
            return this.dive_bomber;
        }

        public static class Engine {

            private String engine_id_str = null;

            private double max_speed = 0;

            private long engine_id = 0;

            public String getEngine_id_str() {
                return this.engine_id_str;
            }

            public double getMax_speed() {
                return this.max_speed;
            }

            public long getEngine_id() {
                return this.engine_id;
            }
        }

        private TorpedoBomber torpedo_bomber = null;

        public static class TorpedoBomber extends PlaneDetails {

            private double torpedo_distance = 0;

            private int torpedo_damage = 0;

            private double torpedo_max_speed = 0;

            private String torpedo_bomber_id_str = null;

            private long torpedo_bomber_id = 0;

            private String torpedo_name = null;

            private int max_damage = 0;

            public double getTorpedo_distance() {
                return this.torpedo_distance;
            }

            public int getTorpedo_damage() {
                return this.torpedo_damage;
            }

            public double getTorpedo_max_speed() {
                return this.torpedo_max_speed;
            }

            public String getTorpedo_bomber_id_str() {
                return this.torpedo_bomber_id_str;
            }

            public long getTorpedo_bomber_id() {
                return this.torpedo_bomber_id;
            }

            public String getTorpedo_name() {
                return this.torpedo_name;
            }

            public int getMax_damage() {
                return this.max_damage;
            }
        }

        private AntiAircraft anti_aircraft = null;

        public static class AntiAircraft {

            private Map<String, AntiAircraftDetails> slots = null;

            public Map<String, AntiAircraftDetails> getSlots() {
                return this.slots;
            }

            public static class AntiAircraftDetails {

                private double distance = 0;

                private double avg_damage = 0;

                private String name = null;

                private int guns = 0;

                public double getDistance() {
                    return this.distance;
                }

                public double getAvg_damage() {
                    return this.avg_damage;
                }

                public String getName() {
                    return this.name;
                }

                public int getGuns() {
                    return this.guns;
                }
            }
        }

        private Mobility mobility = null;

        public static class Mobility {

            private double rudder_time = 0;

            private int total = 0;

            private int turning_radius = 0;

            private double max_speed = 0;

            public double getRudder_time() {
                return this.rudder_time;
            }

            public int getTotal() {
                return this.total;
            }

            public int getTurning_radius() {
                return this.turning_radius;
            }

            public double getMax_speed() {
                return this.max_speed;
            }
        }

        private Hull hull = null;

        public static class Hull {

            private long hull_id = 0;

            private String hull_id_str = null;

            private int torpedoes_barrels = 0;

            private int anti_aircraft_Barrels = 0;

            private Range range = null;

            public long getHull_id() {
                return this.hull_id;
            }

            public String getHull_id_str() {
                return this.hull_id_str;
            }

            public int getTorpedoes_barrels() {
                return this.torpedoes_barrels;
            }

            public int getAnti_aircraft_Barrels() {
                return this.anti_aircraft_Barrels;
            }

            public Range getRange() {
                return this.range;
            }

            public int getHealth() {
                return this.health;
            }

            public int getPlanes_amount() {
                return this.planes_amount;
            }

            public int getArtillery_barrels() {
                return this.artillery_barrels;
            }

            public int getAtba_barrels() {
                return this.atba_barrels;
            }

            public static class Range {

                private int min = 0;

                private int max = 0;

                public int getMin() {
                    return this.min;
                }

                public int getMax() {
                    return this.max;
                }
            }

            private int health = 0;

            private int planes_amount = 0;

            private int artillery_barrels = 0;

            private int atba_barrels = 0;
        }

        /**
         * The secondary armament of the ship
         */
        private Atbas atbas = null;

        /**
         * A class representing information about secondary armament of a ship
         */
        public static class Atbas {

            private double distance = 0;

            private Map<String, ArtilleryDetails> slots = null;

            public double getDistance() {
                return this.distance;
            }

            public Map<String, ArtilleryDetails> getSlots() {
                return this.slots;
            }
        }

        private Artillery artillery = null;

        public static class Artillery {

            private int max_dispersion = 0;

            private Map<ShellType, ArtilleryDetails> shells = null;

            private double shot_delay = 0;

            private double rotation_time = 0;

            private double distance = 0;

            private long artillery_id = 0;

            private String artillery_id_str = null;

            private Map<String, ArtillerySlot> slots = null;

            public int getMax_dispersion() {
                return this.max_dispersion;
            }

            public Map<ShellType, ArtilleryDetails> getShells() {
                return this.shells;
            }

            public double getShot_delay() {
                return this.shot_delay;
            }

            public double getRotation_time() {
                return this.rotation_time;
            }

            public double getDistance() {
                return this.distance;
            }

            public long getArtillery_id() {
                return this.artillery_id;
            }

            public String getArtillery_id_str() {
                return this.artillery_id_str;
            }

            public Map<String, ArtillerySlot> getSlots() {
                return this.slots;
            }

            public double getGun_rate() {
                return this.gun_rate;
            }

            public static class ArtillerySlot {

                private int barrels = 0;

                private String name = null;

                private int guns = 0;

                public int getBarrels() {
                    return this.barrels;
                }

                public String getName() {
                    return this.name;
                }

                public int getGuns() {
                    return this.guns;
                }
            }

            private double gun_rate = 0;
        }

        private Torpedoes torpedoes = null;

        public static class Torpedoes {

            private double visibility_dist = 0;

            private double distance = 0;

            private long torpedoes_id = 0;

            private String torpedo_name = null;

            private double reload_time = 0;

            private double torpedo_speed = 0;

            private double rotation_time = 0;

            private String torpedoes_id_str = null;

            private Map<String, TorpedoSlotDetails> slots = null;

            public double getVisibility_dist() {
                return this.visibility_dist;
            }

            public double getDistance() {
                return this.distance;
            }

            public long getTorpedoes_id() {
                return this.torpedoes_id;
            }

            public String getTorpedo_name() {
                return this.torpedo_name;
            }

            public double getReload_time() {
                return this.reload_time;
            }

            public double getTorpedo_speed() {
                return this.torpedo_speed;
            }

            public double getRotation_time() {
                return this.rotation_time;
            }

            public String getTorpedoes_id_str() {
                return this.torpedoes_id_str;
            }

            public Map<String, TorpedoSlotDetails> getSlots() {
                return this.slots;
            }

            public static class TorpedoSlotDetails {

                private int barrels = 0;

                private double caliber = 0;

                private String name = null;

                private int guns = 0;

                public int getBarrels() {
                    return this.barrels;
                }

                public double getCaliber() {
                    return this.caliber;
                }

                public String getName() {
                    return this.name;
                }

                public int getGuns() {
                    return this.guns;
                }
            }
        }

        private Fighters fighters = null;

        public static class Fighters extends PlaneDetails {

            private long fighters_id = 0;

            private String fighters_id_str = null;

            public long getFighters_id() {
                return this.fighters_id;
            }

            public String getFighters_id_str() {
                return this.fighters_id_str;
            }
        }

        private long ship_id = 0;

        private FireControl fire_control = null;

        public static class FireControl {

            private long fire_control_id = 0;

            private double distance = 0;

            private int distance_increase = 0;

            private String fire_control_id_str = null;

            public long getFire_control_id() {
                return this.fire_control_id;
            }

            public double getDistance() {
                return this.distance;
            }

            public int getDistance_increase() {
                return this.distance_increase;
            }

            public String getFire_control_id_str() {
                return this.fire_control_id_str;
            }
        }

        private Weaponry weaponry = null;

        public static class Weaponry {

            private int anti_aircraft = 0;

            private int aircraft = 0;

            private int artillery = 0;

            private int torpedoes = 0;

            public int getAnti_aircraft() {
                return this.anti_aircraft;
            }

            public int getAircraft() {
                return this.aircraft;
            }

            public int getArtillery() {
                return this.artillery;
            }

            public int getTorpedoes() {
                return this.torpedoes;
            }
        }

        private int battle_level_range_max = 0;

        private int battle_level_range_min = 0;

        private FlightControl flight_control = null;

        public static class FlightControl {

            private String flight_control_id_str = null;

            private int bomber_squadrons = 0;

            private int torpedo_squadrons = 0;

            private long flight_control_id = 0;

            private int fighter_squadrons = 0;

            public String getFlight_control_id_str() {
                return this.flight_control_id_str;
            }

            public int getBomber_squadrons() {
                return this.bomber_squadrons;
            }

            public int getTorpedo_squadrons() {
                return this.torpedo_squadrons;
            }

            public long getFlight_control_id() {
                return this.flight_control_id;
            }

            public int getFighter_squadrons() {
                return this.fighter_squadrons;
            }
        }

        private Concealment concealment = null;

        public static class Concealment {

            private int total = 0;

            private double detect_distance_by_plane = 0;

            private double detect_distance_by_ship = 0;

            public int getTotal() {
                return this.total;
            }

            public double getDetect_distance_by_plane() {
                return this.detect_distance_by_plane;
            }

            public double getDetect_distance_by_ship() {
                return this.detect_distance_by_ship;
            }
        }

        private DiveBomber dive_bomber = null;

        public static class DiveBomber extends PlaneDetails {

            private long dive_bomber_id = 0;

            private double bomb_damage = 0;

            private String bomb_name = null;

            private double bomb_bullet_mass = 0;

            private double bomb_burn_probability = 0;

            private int max_damage = 0;

            private String dive_bomber_id_str = null;

            private SquadCount accuracy = null;

            public long getDive_bomber_id() {
                return this.dive_bomber_id;
            }

            public double getBomb_damage() {
                return this.bomb_damage;
            }

            public String getBomb_name() {
                return this.bomb_name;
            }

            public double getBomb_bullet_mass() {
                return this.bomb_bullet_mass;
            }

            public double getBomb_burn_probability() {
                return this.bomb_burn_probability;
            }

            public int getMax_damage() {
                return this.max_damage;
            }

            public String getDive_bomber_id_str() {
                return this.dive_bomber_id_str;
            }

            public SquadCount getAccuracy() {
                return this.accuracy;
            }
        }

    }

    public static class ArtilleryDetails {

        private double burn_probability = 0;

        private int bullet_speed = 0;

        private String name = null;

        /**
         * Will be 0 for main artillery
         */
        private double shot_delay = 0;

        private int damage = 0;

        private int bullet_mass = 0;

        private ShellType type = ShellType.HE;

        /**
         * Will be 0 for main artillery
         */
        private double gun_rate = 0;

        public double getBurn_probability() {
            return this.burn_probability;
        }

        public int getBullet_speed() {
            return this.bullet_speed;
        }

        public String getName() {
            return this.name;
        }

        public double getShot_delay() {
            return this.shot_delay;
        }

        public int getDamage() {
            return this.damage;
        }

        public int getBullet_mass() {
            return this.bullet_mass;
        }

        public ShellType getType() {
            return this.type;
        }

        public double getGun_rate() {
            return this.gun_rate;
        }
    }

    private static abstract class PlaneDetails {

        private int plane_level = 0;

        private int squadrons = 0;

        private String name = null;

        private double cruise_speed = 0;

        private int prepare_time = 0;

        private SquadCount count_in_squadron = null;

        public int getPlane_level() {
            return this.plane_level;
        }

        public int getSquadrons() {
            return this.squadrons;
        }

        public String getName() {
            return this.name;
        }

        public double getCruise_speed() {
            return this.cruise_speed;
        }

        public int getPrepare_time() {
            return this.prepare_time;
        }

        public SquadCount getCount_in_squadron() {
            return this.count_in_squadron;
        }

        public int getMax_health() {
            return this.max_health;
        }

        public static class SquadCount {

            private int max = 0;

            private int min = 0;

            public int getMax() {
                return this.max;
            }

            public int getMin() {
                return this.min;
            }
        }

        private int max_health = 0;

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
