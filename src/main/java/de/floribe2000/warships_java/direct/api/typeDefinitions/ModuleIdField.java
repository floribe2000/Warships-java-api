package de.floribe2000.warships_java.direct.api.typeDefinitions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ModuleIdField {

    ARTILLERY_ID("artillery_id"),
    DIVE_BOMBER_ID("dive_bomber_id"),
    ENGINE_ID("engine_id"),
    FIGHTER_ID("fighter_id"),
    FIRE_CONTROL_ID("fire_control_id"),
    FLIGHT_CONTROL_ID("flight_control_id"),
    HULL_ID("hull_id"),
    TORPEDO_BOMBER_ID("torpedo_bomber_id"),
    TORPEDOES_ID("torpedoes_id");

    /**
     * The key that is used in urls
     */
    private final String key;

    /**
     * A method used to convert a {@link FieldType} to a string value.
     *
     * @return the field type as request param
     */
    @Override
    public String toString() {
        return "&" + key + "=";
    }

}
