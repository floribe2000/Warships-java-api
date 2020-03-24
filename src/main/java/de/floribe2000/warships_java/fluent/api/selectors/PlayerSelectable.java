package de.floribe2000.warships_java.fluent.api.selectors;

import de.floribe2000.warships_java.fluent.api.Request;

public interface PlayerSelectable<T extends Request> {

	T ofPlayer(long accountId);
}
