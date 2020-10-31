package de.floribe2000.warships_java.direct.encyclopedia

import com.google.gson.annotations.SerializedName
import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.typeDefinitions.*

class Warships : IApiResponse {
    val status = Status.ERROR

    /**
     * Details about errors in case of a failed request.
     *
     * Field is null if no errors occurred during the request!
     */
    override val error: ErrorContainer? = null
    val meta: Meta? = null
    val data: MutableMap<Long, ShipEntry>? = null

    class ShipEntry {

        val description: String? = null

        @SerializedName("price_gold")
        val doubloonPrice = 0

        @SerializedName("ship_id_str")
        val shipIdStr: String? = null

        @SerializedName("has_demo_profile")
        val hasDemoProfile = false

        val images: ImageDetails? = null

        @SerializedName("is_premium")
        val isPremium: Boolean = false

        @SerializedName("is_special")
        val isSpecial: Boolean = false

        class ImageDetails {
            val small: String? = null
            val large: String? = null
            val medium: String? = null
            val contour: String? = null
        }

        val modules: ShipModules? = null

        class ShipModules {
            val engine: List<Long>? = null

            @SerializedName("torpedo_bomber")
            val torpedoBomber: List<Long>? = null

            val fighter: List<Long>? = null

            val hull: List<Long>? = null

            val artillery: List<Long>? = null

            val torpedoes: List<Long>? = null

            @SerializedName("fire_control")
            val fireControl: List<Long>? = null

            @SerializedName("flight_control")
            val flightControl: List<Long>? = null

            @SerializedName("dive_bomber")
            val diveBomber: List<Long>? = null
        }

        @SerializedName("modules_tree")
        val modulesTree: Map<String, ModuleDetails>? = null

        class ModuleDetails {
            val name: String? = null

            @SerializedName("next_modules")
            val nextModules: List<Long>? = null

            @SerializedName("is_default")
            val isDefault: Boolean = false

            @SerializedName("price_xp")
            val xpPrice: Int = 0

            @SerializedName("price_credit")
            val creditPrice: Int = 0

            @SerializedName("next_ships")
            val nextShips: List<Long>? = null

            @SerializedName("module_id")
            val moduleId: Long = 0

            val type: ModuleType? = null

            @SerializedName("module_id_str")
            val moduleIdStr: String? = null
        }

        val nation: Nation? = null

        @SerializedName("ship_id")
        val shipId: Long = 0

        @SerializedName("price_credit")
        val creditPrice: Int = 0

        //TODO default profile configuration
        val upgrades: List<Long>? = null

        val tier: Tier? = null

        @SerializedName("next_ships")
        val nextShips: Map<String, Int>? = null

        @SerializedName("mod_slots")
        val upgradeSlots = 5

        val type: ShipType? = null

        var name: String? = null

        val reducedView: ShipEntryReduced
            get() = ShipEntryReduced(this)

        override fun toString(): String {
            return GSON.toJson(this)
        }
    }

    override fun toString(): String {
        return GSON.toJson(this)
    }
}