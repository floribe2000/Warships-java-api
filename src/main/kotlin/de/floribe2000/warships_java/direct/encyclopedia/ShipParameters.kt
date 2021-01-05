package de.floribe2000.warships_java.direct.encyclopedia

import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.typeDefinitions.ShellType
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status
import kotlinx.serialization.Serializable

//TODO: convert all classes to data classes
/**
 * A representation of an api response for a [ShipParametersRequest].
 * Contains data for a single ship.
 *
 * @author floribe2000
 */
@Suppress("UNUSED", "PropertyName")
@Serializable
data class ShipParameters(
    override val status: Status = Status.ERROR,
    override val error: ErrorContainer? = null,
    override val meta: Meta = Meta(),
    val data: Map<String, ShipParamDetails> = mapOf(),
) : IApiResponse {

    @Serializable
    data class ShipParamDetails(
        val engine: Engine = Engine(),
        val torpedo_bomber: TorpedoBomber? = null,
        val anti_aircraft: AntiAircraft? = null,
        val mobility: Mobility = Mobility(),
        val hull: Hull = Hull(),
        /**
         * The secondary armament of the ship
         */
        val atbas: Atbas? = null,
        val artillery: Artillery? = null,
        val torpedoes: Torpedoes? = null,
        val fighters: Fighters? = null,
        val ship_id: Long = 0,
        val fire_control: FireControl? = null,
        val weaponry: Weaponry = Weaponry(),
        val battle_level_range_max: Int = 0,
        val battle_level_range_min: Int = 0,
        val flight_control: FlightControl? = null,
        val concealment: Concealment = Concealment(),
        val dive_bomber: DiveBomber? = null,
    ) {

        @Serializable
        data class Engine(
            val engine_id_str: String = "",
            val max_speed: Double = 0.0,
            val engine_id: Long = 0,
        )

        @Serializable
        data class TorpedoBomber(
            val torpedo_distance: Double = 0.0,
            val torpedo_damage: Int = 0,
            val torpedo_max_speed: Double = 0.0,
            val torpedo_bomber_id_str: String = "",
            val torpedo_bomber_id: Long = 0,
            val torpedo_name: String = "",
            val max_damage: Int = 0,

            override val plane_level: Int = -1,
            override val squadrons: Int = -1,
            override val name: String = "",
            override val cruise_speed: Double = -1.0,
            override val prepare_time: Double = -1.0,
            override val count_in_squadron: SquadCount = SquadCount(),
            override val max_health: Int = -1,
        ) : PlaneDetails()

        @Serializable
        data class AntiAircraft(
            val slots: Map<String, AntiAircraftDetails> = mapOf(),
        ) {
            @Serializable
            data class AntiAircraftDetails(
                val distance: Double = 0.0,
                val avg_damage: Double = 0.0,
                val name: String = "",
                val guns: Int = 0,
            )
        }

        @Serializable
        data class Mobility(
            val rudder_time: Double = 0.0,
            val total: Int = 0,
            val turning_radius: Int = 0,
            val max_speed: Double = 0.0,
        )

        @Serializable
        data class Hull(
            val hull_id: Long = 0,
            val hull_id_str: String = "",
            val torpedoes_barrels: Int = 0,
            val anti_aircraft_Barrels: Int = 0,
            val range: Range = Range(),
            val health: Int = 0,
            val planes_amount: Int = 0,
            val artillery_barrels: Int = 0,
            val atba_barrels: Int = 0,
        ) {
            @Serializable
            data class Range(
                val min: Int = 0,
                val max: Int = 0,
            )
        }


        /**
         * A class representing information about secondary armament of a ship
         */
        @Serializable
        data class Atbas(
            val distance: Double = 0.0,
            val slots: Map<String, ArtilleryDetails> = mapOf(),
        )

        @Serializable
        data class Artillery(
            val max_dispersion: Int = 0,
            val shells: Map<ShellType, ArtilleryDetails> = mapOf(),
            val shot_delay: Double = 0.0,
            val rotation_time: Double = 0.0,
            val distance: Double = 0.0,
            val artillery_id: Long = 0,
            val artillery_id_str: String = "",
            val slots: Map<String, ArtillerySlot> = mapOf(),
            val gun_rate: Double = 0.0,
        ) {
            @Serializable
            data class ArtillerySlot(
                val barrels: Int = 0,
                val name: String = "",
                val guns: Int = 0,
            )
        }

        @Serializable
        data class Torpedoes(
            val visibility_dist: Double = 0.0,
            val distance: Double = 0.0,
            val torpedoes_id: Long = 0,
            val torpedo_name: String = "",
            val reload_time: Double = 0.0,
            val torpedo_speed: Double = 0.0,
            val rotation_time: Double = 0.0,
            val torpedoes_id_str: String = "",
            val slots: Map<String, TorpedoSlotDetails> = mapOf(),
        ) {
            @Serializable
            data class TorpedoSlotDetails(
                val barrels: Int = 0,
                val caliber: Double = 0.0,
                val name: String = "",
                val guns: Int = 0,
            )
        }

        @Serializable
        data class Fighters(
            val fighters_id: Long = 0,
            val fighters_id_str: String = "",

            override val plane_level: Int = -1,
            override val squadrons: Int = -1,
            override val name: String = "",
            override val cruise_speed: Double = -1.0,
            override val prepare_time: Double = -1.0,
            override val count_in_squadron: SquadCount = SquadCount(),
            override val max_health: Int = -1,
        ) : PlaneDetails()

        @Serializable
        data class FireControl(
            val fire_control_id: Long = 0,
            val distance: Double = 0.0,
            val distance_increase: Int = 0,
            val fire_control_id_str: String = "",
        )

        @Serializable
        data class Weaponry(
            val anti_aircraft: Int = 0,
            val aircraft: Int = 0,
            val artillery: Int = 0,
            val torpedoes: Int = 0,
        )

        @Serializable
        data class FlightControl(
            val flight_control_id_str: String = "",
            val bomber_squadrons: Int = 0,
            val torpedo_squadrons: Int = 0,
            val flight_control_id: Long = 0,
            val fighter_squadrons: Int = 0,
        )

        @Serializable
        data class Concealment(
            val total: Int = 0,
            val detect_distance_by_plane: Double = 0.0,
            val detect_distance_by_ship: Double = 0.0,
        )

        @Serializable
        data class DiveBomber(
            val dive_bomber_id: Long = 0,
            val bomb_damage: Double = 0.0,
            val bomb_name: String = "",
            val bomb_bullet_mass: Double = 0.0,
            val bomb_burn_probability: Double = 0.0,
            val max_damage: Int = 0,
            val dive_bomber_id_str: String = "",
            val accuracy: Accuracy = Accuracy(),

            override val plane_level: Int = -1,
            override val squadrons: Int = -1,
            override val name: String = "",
            override val cruise_speed: Double = -1.0,
            override val prepare_time: Double = -1.0,
            override val count_in_squadron: SquadCount = SquadCount(),
            override val max_health: Int = -1,
        ) : PlaneDetails()
    }

    @Serializable
    data class ArtilleryDetails(
        val burn_probability: Double = 0.0,
        val bullet_speed: Int = 0,
        val name: String = "",

        /**
         * Will be 0 for main artillery
         */
        val shot_delay: Double = 0.0,
        val damage: Int = 0,
        val bullet_mass: Int = 0,
        val type: ShellType = ShellType.HE,

        /**
         * Will be 0 for main artillery
         */
        val gun_rate: Double = 0.0,
    )

    abstract class PlaneDetails {
        abstract val plane_level: Int
        abstract val squadrons: Int
        abstract val name: String?
        abstract val cruise_speed: Double
        abstract val prepare_time: Double
        abstract val count_in_squadron: SquadCount

        @Serializable
        data class SquadCount(
            val max: Int = 0,
            val min: Int = 0,
        )

        @Serializable
        data class Accuracy(
            val max: Double = 0.0,
            val min: Double = 0.0,
        )

        abstract val max_health: Int

        override fun toString(): String {
            return GSON.toJson(this)
        }
    }

    override fun toString(): String {
        return GSON.toJson(this)
    }
}