package de.floribe2000.warships_java.fluent.api.selectors;

import de.floribe2000.warships_java.fluent.api.Request;

public interface LanguageSelectable<T extends Request> {

	T withLanguage(String language);

	T clearLanguage();

}
