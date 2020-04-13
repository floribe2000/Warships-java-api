package de.floribe2000.warships_java.api;

public enum Tier {
	I, II, III, IV, V, VI, VII, VIII, IX, X;

	public static Tier fromInt(int tier) {
		if (tier < 1 || tier > Tier.values().length) {
			throw new IllegalArgumentException("argument has to be between inclusively 1 and 10");
		}
		return Tier.values()[tier - 1];
	}
}
