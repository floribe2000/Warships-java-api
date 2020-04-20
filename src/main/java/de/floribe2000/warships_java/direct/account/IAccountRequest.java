package de.floribe2000.warships_java.direct.account;

import de.floribe2000.warships_java.direct.api.IRequest;

/**
 * An interface only to determine the type of a request.
 * <p>Account requests are requests that contain account details of players. For details see the official api documentation at the
 * <a href="https://developers.wargaming.net/reference/all/wows/">WG Developer Program website</a></p>
 *
 * @param <R> the request type of the implementation of this interface
 * @author floribe2000
 */
public interface IAccountRequest<R> extends IRequest<R> {
}