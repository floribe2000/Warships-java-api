package de.floribe2000.warships_java.fluent.api;

import java.util.Arrays;

public enum GameMode {

	OPERATIONS_SOLO("oper_solo"),
	OPERATIONS_DIV("oper_div"),
	OPERATIONS_DIV_HARD("oper_div_hard"),
	COOP("pve"),
	COOP_SOLO("pve_solo"),
	COOP_DIV2("pve_div2"),
	COOP_DIV3("pve_div3"),
	RANDOM_SOLO("pvp_solo"),
	RANDOM_DIV2("pvp_div2"),
	RANDOM_DIV3("pvp_div3"),
	RANKED_SOLO("rank_solo"),
	RANKED_DIV2("rank_div2"),
	RANKED_DIV3("rank_div3"),
	RANDOM("pvp"); // special case, pvp is a merged entry and standard for each request

//	RANDOM(RANDOM_SOLO, RANDOM_DIV2, RANDOM_DIV3),
//	RANKED(RANKED_SOLO, RANKED_DIV2, RANKED_DIV3),
//	OPERATIONS(OPERATIONS_SOLO, OPERATIONS_DIV, OPERATIONS_DIV_HARD);

	private String requestName;
//	private Set<GameMode> _merged;

	GameMode(String requestName) {
		this.requestName = requestName;
	}

//	GameMode(GameMode... merged) {
//		_merged = new HashSet<>();
//		_merged.addAll(Arrays.asList(merged));
//	}

	public String getRequestName() {
		return requestName;
	}

//	public Set<GameMode> getMerged() {
//		return _merged;
//	}

//	public boolean isMerged() {
//		return _merged != null  && !_merged.isEmpty();
//	}

	public static GameMode valueOfIgnoreCase(String name) {
		return Arrays.stream(GameMode.values()).filter(mode -> mode.toString().equalsIgnoreCase(name)).findFirst().orElse(null);
	}
}
