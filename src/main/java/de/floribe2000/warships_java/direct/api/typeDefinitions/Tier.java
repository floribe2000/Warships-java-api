package de.floribe2000.warships_java.direct.api.typeDefinitions;

import com.google.gson.annotations.SerializedName;

public enum Tier {
	@SerializedName("1") I,
	@SerializedName("2") II,
	@SerializedName("3") III,
	@SerializedName("4") IV,
	@SerializedName("5") V,
	@SerializedName("6") VI,
	@SerializedName("7") VII,
	@SerializedName("8") VIII,
	@SerializedName("9") IX,
	@SerializedName("10") X;

	public static Tier fromInt(int tier) {
		if (tier < 1 || tier > Tier.values().length) {
			throw new IllegalArgumentException("argument has to be between inclusively 1 and 10");
		}
		return Tier.values()[tier - 1];
	}
}
