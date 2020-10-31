package de.floribe2000.warships_java.direct.api

/**
 * An interface to define api fields.
 *
 * Makes sure that all implementations provide a method to retrieve their key.
 */
interface IResponseFields {

    /**
     * The key of the field.
     */
    val key: String

    /**
     * A method to get the key of a response field
     *
     * @return the string value of this field's key
     */
    @Deprecated("replaced by property", replaceWith = ReplaceWith("IResponseFields.key"))
    fun retrieveKey(): String
}