package de.floribe2000.warships_java.direct.api.typeDefinitions;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;

/**
 * An enum that represents all available tiers in the game.
 */
@AllArgsConstructor
public enum Tier implements Comparator<Tier> {
    @SerializedName("1") I(1),
    @SerializedName("2") II(2),
    @SerializedName("3") III(3),
    @SerializedName("4") IV(4),
    @SerializedName("5") V(5),
    @SerializedName("6") VI(6),
    @SerializedName("7") VII(7),
    @SerializedName("8") VIII(8),
    @SerializedName("9") IX(9),
    @SerializedName("10") X(10);

    @Getter
    private final int asInt;

    public static Tier fromInt(int tier) {
        if (tier < 1 || tier > Tier.values().length) {
            throw new IllegalArgumentException("argument has to be between inclusively 1 and 10");
        }
        return Tier.values()[tier - 1];
    }

    @Override
    public int compare(Tier o1, Tier o2) {
        return Integer.compare(o1.getAsInt() - o2.getAsInt(), 0);
    }
}
