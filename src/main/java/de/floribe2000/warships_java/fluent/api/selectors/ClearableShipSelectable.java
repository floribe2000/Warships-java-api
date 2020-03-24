package de.floribe2000.warships_java.fluent.api.selectors;

import de.floribe2000.warships_java.fluent.api.Request;

public interface ClearableShipSelectable<T extends Request> {

	T clearShips();
}
