package de.floribe2000.warships_java.api;

import lombok.NonNull;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * An interface to define a class as request class and provide basic request methods that are needed by all request implementations.
 * <p>Every request should implement this class to avoid code duplication and ensure that basic methods are available.</p>
 *
 * @param <R> the request type of the implementation of this interface
 * @author floribe2000
 */
public interface IRequest<R> {

    /**
     * A default method to create the basic url for a request with mandatory fields as application id and request language.
     * <p>This method returns a raw request url, the {@link IRequestAction#fetch()} method has to make sure to append other fields
     * like account ids or search strings as well as optional parameters.</p>
     * <p>All parameters that have to be added can be added to this string by using a '&' char followed by the field name and the field values.
     * This is done automatically for already implemented fields.</p>
     *
     * @param region   the region for the request
     * @param path     the path for the request
     * @param language the response language
     * @return a string that contains the expandable request url
     */
    @Deprecated
    default String baseUrl(@NonNull Region region, @NonNull String path, Language language) {
        return baseUrl(region, path, language, null);
    }

    /**
     * A default method to create the basic url for a request with mandatory fields as application id and request language.
     * <p>This method returns a raw request url, the {@link IRequestAction#fetch()} method has to make sure to append other fields
     * like account ids or search strings as well as optional parameters.</p>
     * <p>All parameters that have to be added can be added to this string by using a '&' char followed by the field name and the field values.
     * This is done automatically for already implemented fields.</p>
     *
     * @param region       the region for the request
     * @param path         the path for the request
     * @param language     the response language
     * @param instanceName the identifier of the api instance, null for default instance
     * @return a string that contains the expandable request url
     */
    default String baseUrl(@NonNull Region region, @NonNull String path, Language language, String instanceName) {
        return region.getBaseURL() + path + ApiBuilder.getApiKeyAsParam(instanceName) + createLanguageField(language);
    }

    /**
     * A default method that allows to create a string that can be added to the url for an api request from a set of fields.
     * <p>All fields have to implement {@link IResponseFields}.</p>
     *
     * @param type   the name of the field type. Usually "fields" or "extra"
     * @param fields the fields to process
     * @param <T>    the type of the fields to process, must implement {@link IResponseFields}
     * @return A string of the fields that can be added to an api request url
     */
    default <T extends IResponseFields> String buildFieldString(FieldType type, Set<T> fields) {
        StringBuilder strb = new StringBuilder();
        if (fields != null && fields.size() > 0) {
            strb.append(type);
            strb.append(fields.stream().sequential().map(IResponseFields::retrieveKey).collect(Collectors.joining(",")));
        }
        return strb.toString();
    }

    /**
     * A default method that creates the language parameter for the api request.
     * <p>If the language is not specified or is set to {@link Language#ENGLISH} an empty string is returned.</p>
     *
     * @param language the language to use for the request
     * @return the language query param or an empty string if language == null or ENGLISH
     */
    default String createLanguageField(Language language) {
        if (language == null || language == Language.ENGLISH) {
            return "";
        } else {
            return "&language=" + language.getCode();
        }
    }

    /**
     * Defines a region for this request.
     * <p>Can be called multiple times but replaced previous selections!</p>
     *
     * @param region the {@link Region} for this request
     * @return the request instance with an added region
     */
    R region(Region region);

    /**
     * Defines the language of the api response.
     * <p>Only one language can be set for an api response, calling this method multiple times will override existing settings!</p>
     *
     * @param language the language to set
     * @return the request instance with an added language
     */
    R language(Language language);
}
