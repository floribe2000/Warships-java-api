package de.floribe2000.warships_java.direct.api;

/**
 * A representation of the error field of a failed api request.
 *
 * @author floribe2000
 */
public class ErrorContainer {

    private String field = null;

    private String message = null;

    private int code = 0;

    private String value = null;

    @Override
    public String toString() {
        return IRequestAction.GSON.toJson(this);
    }

    public String getField() {
        return this.field;
    }

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }

    public String getValue() {
        return this.value;
    }
}
