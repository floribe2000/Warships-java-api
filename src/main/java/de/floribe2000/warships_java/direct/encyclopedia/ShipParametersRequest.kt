package de.floribe2000.warships_java.direct.encyclopedia

import de.floribe2000.warships_java.direct.api.AbstractRequest
import de.floribe2000.warships_java.direct.api.typeDefinitions.FieldType
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.ModuleIdField
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import java.util.*

/**
 * A class to create a ShipParameters encyclopedia request
 */
class ShipParametersRequest : AbstractRequest<ShipParametersRequest, ShipParameters>() {
    /**
     * The server region for this request
     */
    private lateinit var selectedRegion: Region

    /**
     * The language for the api response
     */
    private var language: Language? = null

    /**
     * The ship id for the request
     */
    private var shipId: Long = 0

    /**
     * A map containing lists of additional module ids for the request.
     */
    private val additionalParams: MutableMap<ModuleIdField, Long> = EnumMap(ModuleIdField::class.java)

    override fun region(region: Region): ShipParametersRequest {
        this.selectedRegion = region
        return this
    }

    override fun language(language: Language): ShipParametersRequest {
        this.language = language
        return this
    }

    override fun apiBuilder(instanceName: String): ShipParametersRequest {
        setInstance(instanceName)
        return this
    }

    /**
     * Defines the ship id for this request.
     *
     * @param shipId the id of the ship
     * @return an instance of this request
     */
    fun shipId(shipId: Long): ShipParametersRequest {
        this.shipId = shipId
        return this
    }

    /**
     * Adds an additional parameter to the request.
     *
     * If there is already a value defined for this field, the value will not be replaced
     *
     * @param field    the field type to add
     * @param moduleId the module id to add
     * @return the instance of this request
     */
    fun addAdditionalParam(field: ModuleIdField, moduleId: Long): ShipParametersRequest {
        additionalParams[field] = moduleId
        return this
    }

    /**
     * Removes all values of the given field type.
     *
     * @param field the field type to remove from the request
     * @return the instance of this request
     */
    fun removeAdditionalParam(field: ModuleIdField): ShipParametersRequest {
        additionalParams.remove(field)
        return this
    }

    /**
     * Clears all additional request fields.
     *
     * @return the instance of this request
     */
    fun clearAdditionalParams(): ShipParametersRequest {
        additionalParams.clear()
        return this
    }

    override fun buildUrl(): String {
        require(this::selectedRegion.isInitialized) { "The region has to be initialized." }
        val path = "/wows/encyclopedia/shipprofile/"
        val stringBuilder = StringBuilder()
        for ((key, value) in additionalParams) {
            stringBuilder.append(key.toString()).append(value)
        }
        return baseUrl(selectedRegion, path, language, instanceName) + FieldType.SHIP_ID + shipId + stringBuilder.toString()
    }

    override fun fetch(url: String): ShipParameters {
        return connect(url, ShipParameters::class.java, limiter)
    }

    companion object {
        /**
         * Creates a new empty request of this class.
         *
         * @return an instance of this class
         */
        fun createRequest(): ShipParametersRequest {
            return ShipParametersRequest()
        }
    }
}