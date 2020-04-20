package de.floribe2000.warships_java.direct.api;

/**
 * An interface to define api fields.
 * <p>Makes sure that all implementations provide a method to retrieve their key.</p>
 */
public interface IResponseFields {

    /**
     * A method to get the key of a response field
     *
     * @return the string value of this field's key
     */
    String retrieveKey();
}
