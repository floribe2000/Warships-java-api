package de.floribe2000.warships_java.direct.api.typeDefinitions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FieldType {
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

    private String key;

    @Override
    public String toString() {
        return "&" + key + "=";
    }

    /**
     * A method used to convert a {@link FieldType} to a request param
     *
     * @param type the field type for this field
     * @return the field type as request param
     */
    public static String getAsParam(FieldType type) {
        return "&" + type.getKey() + "=";
    }
}
