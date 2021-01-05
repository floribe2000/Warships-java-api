package de.floribe2000.warships_java.direct.api

import de.floribe2000.warships_java.direct.api.typeDefinitions.FieldType
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region

/**
 * An interface to define a class as request class and provide basic request methods that are needed by all request implementations.
 *
 * Every request should implement this class to avoid code duplication and ensure that basic methods are available.
 *
 * @param <R> the request type of the implementation of this interface
 * @author floribe2000
</R> */
interface IRequest<R : Any> {
    /**
     * A default method to create the basic url for a request with mandatory fields as application id and request language.
     *
     * This method returns a raw request url, the [IRequestAction.fetch] method has to make sure to append other fields
     * like account ids or search strings as well as optional parameters.
     *
     * All parameters that have to be added can be added to this string by using a '&amp;' char followed by the field name and the field values.
     * This is done automatically for already implemented fields.
     *
     * @param region       the region for the request
     * @param path         the path for the request
     * @param language     the response language
     * @param instanceName the identifier of the api instance, null for default instance
     * @return a string that contains the expandable request url
     */
    @JvmDefault
    fun baseUrl(region: Region, path: String, language: Language?, instanceName: String?): String {
        return region.baseURL + path + ApiBuilder.getApiKeyAsParam(instanceName) + createLanguageField(language)
    }

    /**
     * A default method that allows to create a string that can be added to the url for an api request from a set of fields.
     *
     * All fields have to implement [IResponseFields].
     *
     * @param type   the name of the field type. Usually "fields" or "extra"
     * @param fields the fields to process
     * @param <T>    the type of the fields to process, must implement [IResponseFields]
     * @return A string of the fields that can be added to an api request url
    </T> */
    @JvmDefault
    fun <T : IResponseFields> buildFieldString(type: FieldType, fields: Set<T>): String {
        val builder = StringBuilder()
        if (fields.isNotEmpty()) {
            builder.append(type)
            builder.append(fields.joinToString(separator = ",") { entry -> entry.key })
        }
        return builder.toString()
    }

    /**
     * A default method that creates the language parameter for the api request.
     *
     * If the language is not specified or is set to [Language.ENGLISH] an empty string is returned.
     *
     * @param language the language to use for the request
     * @return the language query param or an empty string if language == null or ENGLISH
     */
    @JvmDefault
    fun createLanguageField(language: Language?): String {
        return if (language == null || language == Language.ENGLISH) {
            ""
        } else {
            "&language=" + language.code
        }
    }

    /**
     * Defines a region for this request.
     *
     * Can be called multiple times but replaced previous selections!
     *
     * @param region the [Region] for this request
     * @return the request instance with an added region
     */
    fun region(region: Region): R

    /**
     * Defines the language of the api response.
     *
     * Only one language can be set for an api response, calling this method multiple times will override existing settings!
     *
     * @param language the language to set
     * @return the request instance with an added language
     */
    fun language(language: Language): R

    /**
     * Defines the api builder instance for this request.
     *
     * @param instanceName the name of the api builder instance
     * @return the request instance with an added api builder instance
     */
    fun apiBuilder(instanceName: String): R
}