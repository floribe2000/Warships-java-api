package de.floribe2000.warships_java.direct.encyclopedia

import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.typeDefinitions.ShellType
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status

/**
 * A representation of an api response for a [ShipParametersRequest].
 * Contains data for a single ship.
 *
 * @author floribe2000
 */
class ShipParameters : IApiResponse {
    val status = Status.ERROR

    override val error: ErrorContainer? = null
    val meta: Meta? = null
    val data: Map<String, ShipParamDetails>? = null

    class ShipParamDetails {
        val engine: Engine? = null

        class Engine {
            val engine_id_str: String? = null
            val max_speed = 0.0
            val engine_id: Long = 0
        }

        val torpedo_bomber: TorpedoBomber? = null

        class TorpedoBomber : PlaneDetails() {
            val torpedo_distance = 0.0
            val torpedo_damage = 0
            val torpedo_max_speed = 0.0
            val torpedo_bomber_id_str: String? = null
            val torpedo_bomber_id: Long = 0
            val torpedo_name: String? = null
            val max_damage = 0
        }

        val anti_aircraft: AntiAircraft? = null

        class AntiAircraft {
            val slots: Map<String, AntiAircraftDetails>? = null

            class AntiAircraftDetails {
                val distance = 0.0
                val avg_damage = 0.0
                val name: String? = null
                val guns = 0
            }
        }

        val mobility: Mobility? = null

        class Mobility {
            val rudder_time = 0.0
            val total = 0
            val turning_radius = 0
            val max_speed = 0.0
        }

        val hull: Hull? = null

        class Hull {
            val hull_id: Long = 0
            val hull_id_str: String? = null
            val torpedoes_barrels = 0
            val anti_aircraft_Barrels = 0
            val range: Range? = null

            class Range {
                val min = 0
                val max = 0
            }

            val health = 0
            val planes_amount = 0
            val artillery_barrels = 0
            val atba_barrels = 0
        }

        /**
         * The secondary armament of the ship
         */
        val atbas: Atbas? = null

        /**
         * A class representing information about secondary armament of a ship
         */
        class Atbas {
            val distance = 0.0
            val slots: Map<String, ArtilleryDetails>? = null
        }

        val artillery: Artillery? = null

        class Artillery {
            val max_dispersion = 0
            val shells: Map<ShellType, ArtilleryDetails>? = null
            val shot_delay = 0.0
            val rotation_time = 0.0
            val distance = 0.0
            val artillery_id: Long = 0
            val artillery_id_str: String? = null
            val slots: Map<String, ArtillerySlot>? = null

            class ArtillerySlot {
                val barrels = 0
                val name: String? = null
                val guns = 0
            }

            val gun_rate = 0.0
        }

        val torpedoes: Torpedoes? = null

        class Torpedoes {
            val visibility_dist = 0.0
            val distance = 0.0
            val torpedoes_id: Long = 0
            val torpedo_name: String? = null
            val reload_time = 0.0
            val torpedo_speed = 0.0
            val rotation_time = 0.0
            val torpedoes_id_str: String? = null
            val slots: Map<String, TorpedoSlotDetails>? = null

            class TorpedoSlotDetails {
                val barrels = 0
                val caliber = 0.0
                val name: String? = null
                val guns = 0
            }
        }

        val fighters: Fighters? = null

        class Fighters : PlaneDetails() {
            val fighters_id: Long = 0
            val fighters_id_str: String? = null
        }

        val ship_id: Long = 0
        val fire_control: FireControl? = null

        class FireControl {
            val fire_control_id: Long = 0
            val distance = 0.0
            val distance_increase = 0
            val fire_control_id_str: String? = null
        }

        val weaponry: Weaponry? = null

        class Weaponry {
            val anti_aircraft = 0
            val aircraft = 0
            val artillery = 0
            val torpedoes = 0
        }

        val battle_level_range_max = 0
        val battle_level_range_min = 0
        val flight_control: FlightControl? = null

        class FlightControl {
            val flight_control_id_str: String? = null
            val bomber_squadrons = 0
            val torpedo_squadrons = 0
            val flight_control_id: Long = 0
            val fighter_squadrons = 0
        }

        val concealment: Concealment? = null

        class Concealment {
            val total = 0
            val detect_distance_by_plane = 0.0
            val detect_distance_by_ship = 0.0
        }

        val dive_bomber: DiveBomber? = null

        class DiveBomber : PlaneDetails() {
            val dive_bomber_id: Long = 0
            val bomb_damage = 0.0
            val bomb_name: String? = null
            val bomb_bullet_mass = 0.0
            val bomb_burn_probability = 0.0
            val max_damage = 0
            val dive_bomber_id_str: String? = null
            val accuracy: SquadCount? = null
        }
    }

    class ArtilleryDetails {
        val burn_probability = 0.0
        val bullet_speed = 0
        val name: String? = null

        /**
         * Will be 0 for main artillery
         */
        val shot_delay = 0.0
        val damage = 0
        val bullet_mass = 0
        val type = ShellType.HE

        /**
         * Will be 0 for main artillery
         */
        val gun_rate = 0.0
    }

    abstract class PlaneDetails {
        val plane_level = 0
        val squadrons = 0
        val name: String? = null
        val cruise_speed = 0.0
        val prepare_time = 0
        val count_in_squadron: SquadCount? = null

        class SquadCount {
            val max = 0
            val min = 0
        }

        val max_health = 0

        override fun toString(): String {
            return GSON.toJson(this)
        }
    }

    override fun toString(): String {
        return GSON.toJson(this)
    }
}