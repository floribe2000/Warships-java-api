package de.floribe2000.warships_java.direct.api.typeDefinitions

/**
 * All available field types.
 *
 * Please note that not all field types are available for every request! For details see the documentation of the specific request.
 */
enum class FieldType(
        /**
         * The key that is used in urls
         */
        val key: String) {

    FIELDS("fields"),
    EXTRA("extra"),
    ACCOUNT_ID("account_id"),
    DATES("dates"),
    PAGE("page_no"),
    SHIP_ID("ship_id"),
    SHIP_CLASS("type"),
    NATION("nation"),
    LIMIT("limit"),
    SEARCH("search"),
    CLAN("clan_id"),
    SEASON_ID("season_id");

    override fun toString(): String {
        return "&$key="
    }

    companion object {
        /**
         * A method used to convert a [FieldType] to a request param
         *
         * @param type the field type for this field
         * @return the field type as request param
         */
        fun getAsParam(type: FieldType): String {
            return "&" + type.key + "="
        }
    }
}