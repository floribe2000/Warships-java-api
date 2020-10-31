package de.floribe2000.warships_java.direct.api

/**
 * An interface to mark a class as Pojo of an api response.
 * All Pojos should implement this interface.
 *
 * @author floribe2000
 */
interface IApiResponse {

    val error: ErrorContainer?
}