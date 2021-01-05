package de.floribe2000.warships_java.direct.api.typeDefinitions

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * An enum that represents all available tiers in the game.
 */
@Serializable
enum class Tier(val asInt: Int) : Comparator<Tier> {
    @SerializedName("1")
    @SerialName("1")
    I(1),

    @SerializedName("2")
    @SerialName("2")
    II(2),

    @SerializedName("3")
    @SerialName("3")
    III(3),

    @SerializedName("4")
    @SerialName("4")
    IV(4),

    @SerializedName("5")
    @SerialName("5")
    V(5),

    @SerializedName("6")
    @SerialName("6")
    VI(6),

    @SerializedName("7")
    @SerialName("7")
    VII(7),

    @SerializedName("8")
    @SerialName("8")
    VIII(8),

    @SerializedName("9")
    @SerialName("9")
    IX(9),

    @SerializedName("10")
    @SerialName("10")
    X(10);

    override fun compare(o1: Tier, o2: Tier): Int {
        return o1.asInt.compareTo(o2.asInt)
    }

    companion object {
        fun fromInt(tier: Int): Tier {
            require(!(tier < 1 || tier > values().size)) { "argument has to be between inclusively 1 and 10" }
            return values()[tier - 1]
        }
    }
}