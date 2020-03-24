package de.floribe2000.warships_java.fluent.api.selectors;

import de.floribe2000.warships_java.fluent.api.Request;
import java.util.Collection;

public interface FieldsSelectable<T extends Request> {

	T withField(String field);

	T withFields(String... fields);

	T withFields(Collection<String> fields);

	T clearFields();

}
