package de.floribe2000.warships_java.direct.api.typeDefinitions

@Suppress("unused")
enum class ModuleIdField(
    /**
     * The key that is used in urls
     */
    private val key: String,
) {
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
     * A method used to convert a [FieldType] to a string value.
     *
     * @return the field type as request param
     */
    override fun toString(): String {
        return "&$key="
    }
}