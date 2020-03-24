package de.floribe2000.warships_java.api;

import java.util.Set;

/**
 * An interface to define a class as request class and provide basic request methods that are needed by all request implementations.
 * <p>Every request should implement this class to avoid code duplication and ensure that basic methods are available.</p>
 *
 * @param <R> the request type of the implementation of this interface
 * @author floribe2000
 */
public interface IRequest<R> {

    /**
     * A default method that allows to create a string that can be added to the url for an api request from a set of fields.
     * <p>All fields have to implement {@link IResponseFields}.</p>
     *
     * @param type   the name of the field type. Usually "fields" or "extra"
     * @param fields the fields to process
     * @param <T>    the type of the fields to process, must implement {@link IResponseFields}
     * @return A string of the fields that can be added to an api request url
     */
    default <T extends IResponseFields> String buildFieldString(String type, Set<T> fields) {
        StringBuilder strb = new StringBuilder();
        if (fields != null && fields.size() > 0) {
            strb.append("&").append(type).append("=");
            String prefix = "";
            for (IResponseFields field : fields) {
                strb.append(prefix);
                strb.append(field.retrieveKey());
                prefix = ",";
            }
        }
        return strb.toString();
    }

    /**
     * Defines a region for this request.
     * <p>Can be called multiple times but replaced previous selections!</p>
     *
     * @param region the {@link Region} for this request
     * @return the request instance with an added region
     */
    R region(Region region);
}
