package de.floribe2000.warships_java.direct.encyclopedia;

import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.typeDefinitions.ShellType;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;
import lombok.Getter;

import java.util.Map;

/**
 * A representation of an api response for a {@link ShipParametersRequest}.
 * Contains data for a single ship.
 *
 * @author floribe2000
 */
@Getter
public class ShipParameters implements IApiResponse {

    private Status status = Status.ERROR;

    private Meta meta = null;

    private Map<String, ShipParamDetails> data = null;

    @Getter
    public static class ShipParamDetails {

        private Engine engine = null;

        @Getter
        public static class Engine {

            private String engine_id_str = null;

            private double max_speed = 0;

            private long engine_id = 0;
        }

        private TorpedoBomber torpedo_bomber = null;

        @Getter
        public static class TorpedoBomber extends PlaneDetails {

            private double torpedo_distance = 0;

            private int torpedo_damage = 0;

            private double torpedo_max_speed = 0;

            private String torpedo_bomber_id_str = null;

            private long torpedo_bomber_id = 0;

            private String torpedo_name = null;

            private int max_damage = 0;
        }

        private AntiAircraft anti_aircraft = null;

        @Getter
        public static class AntiAircraft {

            private Map<String, AntiAircraftDetails> slots = null;

            @Getter
            public static class AntiAircraftDetails {

                private double distance = 0;

                private double avg_damage = 0;

                private String name = null;

                private int guns = 0;
            }
        }

        private Mobility mobility = null;

        @Getter
        public static class Mobility {

            private double rudder_time = 0;

            private int total = 0;

            private int turning_radius = 0;

            private double max_speed = 0;
        }

        private Hull hull = null;

        @Getter
        public static class Hull {

            private long hull_id = 0;

            private String hull_id_str = null;

            private int torpedoes_barrels = 0;

            private int anti_aircraft_Barrels = 0;

            private Range range = null;

            @Getter
            public static class Range {

                private int min = 0;

                private int max = 0;
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
        @Getter
        public static class Atbas {

            private double distance = 0;

            private Map<String, ArtilleryDetails> slots = null;
        }

        private Artillery artillery = null;

        @Getter
        public static class Artillery {

            private int max_dispersion = 0;

            private Map<ShellType, ArtilleryDetails> shells = null;

            private double shot_delay = 0;

            private double rotation_time = 0;

            private double distance = 0;

            private long artillery_id = 0;

            private String artillery_id_str = null;

            private Map<String, ArtillerySlot> slots = null;

            @Getter
            public static class ArtillerySlot {

                private int barrels = 0;

                private String name = null;

                private int guns = 0;
            }

            private double gun_rate = 0;
        }

        private Torpedoes torpedoes = null;

        @Getter
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

            @Getter
            public static class TorpedoSlotDetails {

                private int barrels = 0;

                private double caliber = 0;

                private String name = null;

                private int guns = 0;
            }
        }

        private Fighters fighters = null;

        @Getter
        public static class Fighters extends PlaneDetails {

            private long fighters_id = 0;

            private String fighters_id_str = null;
        }

        private long ship_id = 0;

        private FireControl fire_control = null;

        @Getter
        public static class FireControl {

            private long fire_control_id = 0;

            private double distance = 0;

            private int distance_increase = 0;

            private String fire_control_id_str = null;
        }

        private Weaponry weaponry = null;

        @Getter
        public static class Weaponry {

            private int anti_aircraft = 0;

            private int aircraft = 0;

            private int artillery = 0;

            private int torpedoes = 0;
        }

        private int battle_level_range_max = 0;

        private int battle_level_range_min = 0;

        private FlightControl flight_control = null;

        @Getter
        public static class FlightControl {

            private String flight_control_id_str = null;

            private int bomber_squadrons = 0;

            private int torpedo_squadrons = 0;

            private long flight_control_id = 0;

            private int fighter_squadrons = 0;
        }

        private Concealment concealment = null;

        @Getter
        public static class Concealment {

            private int total = 0;

            private double detect_distance_by_plane = 0;

            private double detect_distance_by_ship = 0;
        }

        private DiveBomber dive_bomber = null;

        @Getter
        public static class DiveBomber extends PlaneDetails {

            private long dive_bomber_id = 0;

            private double bomb_damage = 0;

            private String bomb_name = null;

            private double bomb_bullet_mass = 0;

            private double bomb_burn_probability = 0;

            private int max_damage = 0;

            private String dive_bomber_id_str = null;

            private SquadCount accuracy = null;
        }

    }

    @Getter
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
    }

    @Getter
    private static abstract class PlaneDetails {

        private int plane_level = 0;

        private int squadrons = 0;

        private String name = null;

        private double cruise_speed = 0;

        private int prepare_time = 0;

        private SquadCount count_in_squadron = null;

        @Getter
        public static class SquadCount {

            private int max = 0;

            private int min = 0;
        }

        private int max_health = 0;

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
