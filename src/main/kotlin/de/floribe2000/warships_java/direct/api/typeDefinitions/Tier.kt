package de.floribe2000.warships_java.direct.api.typeDefinitions

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * An enum that represents all available tiers in the game.
 */
@Serializable
@Suppress("UNUSED")
enum class Tier(val intValue: Int) {
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

    operator fun rangeTo(other: Tier) = TierRange(this, other)

    companion object {
        fun fromInt(tier: Int): Tier {
            require(!(tier < 1 || tier > values().size)) { "argument has to be between inclusively 1 and 10" }
            return values()[tier - 1]
        }
    }
}

/**
 * Utility class to allow using tiers in ranges.
 */
class TierRange(override val start: Tier, override val endInclusive: Tier) : ClosedRange<Tier>, Iterable<Tier> {
    override fun iterator(): Iterator<Tier> {
        return TierIterator(start, endInclusive)
    }
}

/**
 * An iterator for the [TierRange] class.
 */
class TierIterator(start: Tier, private val endInclusive: Tier) : Iterator<Tier> {

    /**
     * An integer used as backing field to allow iterating through the tiers.
     */
    private var currentValue: Int = start.intValue

    override fun hasNext(): Boolean {
        return currentValue <= endInclusive.intValue
    }

    override fun next(): Tier {
        return Tier.fromInt(currentValue++)
    }

}