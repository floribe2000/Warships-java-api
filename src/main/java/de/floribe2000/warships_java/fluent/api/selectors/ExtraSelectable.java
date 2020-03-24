package de.floribe2000.warships_java.fluent.api.selectors;

import de.floribe2000.warships_java.fluent.api.Request;
import java.util.Collection;

public interface ExtraSelectable<T extends Request> {

	T withExtra(String extra);

	T withExtras(String... extras);

	T withExtras(Collection<String> extras);

	T clearExtras();

}
