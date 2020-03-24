package de.floribe2000.warships_java.fluent.api.selectors;

import de.floribe2000.warships_java.fluent.api.Request;
import java.util.Collection;

public interface ShipSelectable<T extends Request> {

	T ofShip(long shipId);

	T ofShips(Long... shipIds);

	T ofShips(Collection<Long> shipIds);

}
