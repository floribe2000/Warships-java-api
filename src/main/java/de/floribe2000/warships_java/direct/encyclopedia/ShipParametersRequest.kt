package de.floribe2000.warships_java.direct.encyclopedia;

import de.floribe2000.warships_java.direct.api.AbstractRequest;
import de.floribe2000.warships_java.direct.api.typeDefinitions.FieldType;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language;
import de.floribe2000.warships_java.direct.api.typeDefinitions.ModuleIdField;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to create a ShipParameters encyclopedia request
 */
public class ShipParametersRequest extends AbstractRequest<ShipParametersRequest, ShipParameters> {

    /**
     * The server region for this request
     */
    private Region region = null;

    /**
     * The language for the api response
     */
    private Language language = null;

    /**
     * The ship id for the request
     */
    private long shipId = 0;

    /**
     * A map containing lists of additional module ids for the request.
     */
    private Map<ModuleIdField, Long> additionalParams = new HashMap<>();

    public ShipParametersRequest() {
    }

    /**
     * Creates a new empty request of this class.
     *
     * @return an instance of this class
     */
    public static ShipParametersRequest createRequest() {
        return new ShipParametersRequest();
    }

    @Override
    public ShipParametersRequest region(Region region) {
        this.region = region;
        return this;
    }

    @Override
    public ShipParametersRequest language(Language language) {
        this.language = language;
        return this;
    }

    @Override
    public ShipParametersRequest apiBuilder(String instanceName) {
        setInstance(instanceName);
        return this;
    }

    /**
     * Defines the ship id for this request.
     *
     * @param shipId the id of the ship
     * @return an instance of this request
     */
    public ShipParametersRequest shipId(long shipId) {
        this.shipId = shipId;
        return this;
    }

    /**
     * Adds an additional parameter to the request.
     * <p>If there is already a value defined for this field, the value will not be replaced</p>
     *
     * @param field    the field type to add
     * @param moduleId the module id to add
     * @return the instance of this request
     */
    public ShipParametersRequest addAdditionalParam(ModuleIdField field, long moduleId) {
        additionalParams.put(field, moduleId);
        return this;
    }

    /**
     * Removes all values of the given field type.
     *
     * @param field the field type to remove from the request
     * @return the instance of this request
     */
    public ShipParametersRequest removeAdditionalParam(ModuleIdField field) {
        additionalParams.remove(field);
        return this;
    }

    /**
     * Clears all additional request fields.
     *
     * @return the instance of this request
     */
    public ShipParametersRequest clearAdditionalParams() {
        additionalParams.clear();
        return this;
    }

    @Override
    public String buildUrl() {
        checkRegion(region);
        String path = "/wows/encyclopedia/shipprofile/";
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<ModuleIdField, Long> entry : additionalParams.entrySet()) {
            stringBuilder.append(entry.getKey().toString()).append(entry.getValue());
        }
        return baseUrl(region, path, language, getInstanceName()) + FieldType.SHIP_ID + shipId + stringBuilder.toString();
    }

    @Override
    protected ShipParameters fetch(String url) {
        return connect(url, ShipParameters.class, getLimiter());
    }
}
