package de.floribe2000.warships_java.direct.api;

import lombok.Getter;

/**
 * A representation of the error field of a failed api request.
 *
 * @author floribe2000
 */
@Getter
public class ErrorContainer {

    private String field = null;

    private String message = null;

    private int code = 0;

    private String value = null;
}
