package de.floribe2000.warships_java.direct.account

import de.floribe2000.warships_java.direct.api.AbstractRequest
import de.floribe2000.warships_java.direct.api.IResponseFields
import de.floribe2000.warships_java.direct.api.typeDefinitions.FieldType
import de.floribe2000.warships_java.direct.api.typeDefinitions.Language
import de.floribe2000.warships_java.direct.api.typeDefinitions.Region
import org.slf4j.LoggerFactory
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit

class PlayerStatisticsByDateRequest : AbstractRequest<PlayerStatisticsByDateRequest, PlayersStatisticsByDate>() {
    /**
     * The server region for this request
     */
    private lateinit var selectedRegion: Region

    /**
     * The language for the api response
     */
    private var language: Language? = null

    /**
     * The account id for this request
     */
    private var accountId: Long = 0

    /**
     * The list of dates for this request. A maximum of 10 dates is allowed.
     */
    private var dates: MutableList<String> = ArrayList()

    /**
     * The extra field for this request. Only one field is possible in this case.
     */
    private var extra: ExtraField? = null
    override fun region(region: Region): PlayerStatisticsByDateRequest {
        this.selectedRegion = region
        return this
    }

    override fun language(language: Language): PlayerStatisticsByDateRequest {
        this.language = language
        return this
    }

    override fun buildUrl(): String {
        require(this::selectedRegion.isInitialized && accountId > 0) { "Region must not be null and accountId has to be defined." }
        val path = "/wows/account/statsbydate/"
        val dates = dates.joinToString { "," }
        val extra = if (extra != null) FieldType.EXTRA.toString() + extra!!.retrieveKey() else ""
        return baseUrl(selectedRegion, path, language, instanceName) + FieldType.ACCOUNT_ID + accountId + FieldType.DATES + dates + extra
    }

    override fun apiBuilder(instanceName: String): PlayerStatisticsByDateRequest {
        setInstance(instanceName)
        return this
    }

    /**
     * Sets the account id for this request. An existing id will be replaced.
     *
     * @param accountId the account id for the request
     * @return the instance of this request
     */
    fun accountId(accountId: Long): PlayerStatisticsByDateRequest {
        this.accountId = accountId
        return this
    }

    /**
     * Sets the list of dates for this request. A maximum of 10 dates is allowed.
     *
     * Existing dates will be replaced!
     *
     * @param dates the list of dates for this request
     * @return the instance of this request
     * @throws IllegalArgumentException
     *  * If the date format of the string doesn't match the pattern yyyyMMdd
     *  * If the provided collection of dates has more than 10 elements
     *
     */
    fun dates(dates: Collection<String>): PlayerStatisticsByDateRequest {
        if (dates.size <= 10) {
            dates.forEach {
                require(checkStringFormatting(it)) { "Dateformat doesn't match the requirements" }
            }
            this.dates = ArrayList(dates)
        } else {
            throw IllegalArgumentException("You can only specify a maximum of 10 dates.")
        }
        return this
    }

    /**
     * Adds a single date to this request.
     *
     * Existing dates won't be changed!
     *
     * @param date the date to add
     * @return the instance of this request
     * @throws IllegalStateException    If there are already 10 dates set for this request
     * @throws IllegalArgumentException If the date format of the string doesn't match the pattern yyyyMMdd
     */
    fun addDate(date: String): PlayerStatisticsByDateRequest {
        check(dates.size < 10) { "You can't set more than 10 dates." }
        if (checkStringFormatting(date)) {
            dates.add(date)
        } else {
            throw IllegalArgumentException("The date has to match the pattern yyyyMMdd")
        }
        return this
    }

    /**
     * Adds a single date to this request.
     *
     * Existing dates won't be changed!
     *
     * This method also checks the validity of the date, it must be between today and 28 days ago.
     *
     * @param date the date to add
     * @return the instance of this request
     * @throws IllegalArgumentException If the date is not valid, for example if it's more than 28 days in the past
     * @throws IllegalStateException    If there are already 10 dates set for this request
     */
    fun addDate(date: ZonedDateTime): PlayerStatisticsByDateRequest {
        require(validateDate(date)) { "Date is not valid" }
        if (dates.size < 10) {
            dates.add(df.format(date))
        } else {
            throw IllegalStateException("You can't set more than 10 dates.")
        }
        return this
    }

    /**
     * Sets the extra field for this request.
     *
     * Replaces existing values!
     *
     * In this case there is only one field available to this method accepts either [ExtraField.PVE] or null.
     *
     * @param extra the extra field for this request
     * @return the instance of this request
     */
    fun extra(extra: ExtraField?): PlayerStatisticsByDateRequest {
        this.extra = extra
        return this
    }

    /**
     * Executes a request and returns the result of the request.
     *
     * All requests are executed synchronous on this thread. It is safe to execute this in a new thread if it is required to be run asynchronous.
     *
     * @return an instance of [PlayersStatisticsByDate] that contains all requested player data. If the request fails, an empty instance is returned.
     * @throws IllegalArgumentException
     *  * If this method is called and region is null.
     *  * If this method is called and no account id has been defined.
     *
     */
    override fun fetch(url: String): PlayersStatisticsByDate {
        return connect(url, PlayersStatisticsByDate::class.java, limiter)
    }

    /**
     * All possible extra fields for this request.
     *
     * These fields are optional fields, the request will also work without them.
     */
    enum class ExtraField(private val key: String) : IResponseFields {
        /**
         * The coop battles mode
         */
        PVE("pve");

        override fun retrieveKey(): String {
            return key
        }
    }

    companion object {
        /**
         * A Logger instance used to log events of this class
         */
        private val LOG = LoggerFactory.getLogger(PlayerStatisticsByDateRequest::class.java.simpleName)

        /**
         * The date format pattern used for the date field
         */
        private const val DATE_FORMAT = "yyyyMMdd"

        /**
         * The [java.text.DateFormat] instance used to format dates that can be used by the api
         */
        private val df = DateTimeFormatter.ofPattern(DATE_FORMAT)

        /**
         * Creates a new empty request instance of this class.
         *
         * @return a new instance of this class
         */
        fun createRequest(): PlayerStatisticsByDateRequest {
            return PlayerStatisticsByDateRequest()
        }

        /**
         * A utility method to allow validating a date.
         *
         * Parses the given string to a date and returns the result of the check for this date.
         *
         * @param date the date as string
         * @return true if the date is valid, false if the date is invalid or the string doesn't match the required format
         */
        @Deprecated("")
        private fun validateDate(date: String): Boolean {
            return if (checkStringFormatting(date)) {
                try {
                    validateDate(LocalDate.parse(date, df).atStartOfDay(ZoneId.systemDefault()))
                } catch (e: DateTimeParseException) {
                    LOG.warn("Parsing error", e)
                    false
                }
            } else false
        }

        /**
         * A utility method to validate a date.
         *
         * Checks if the date is not longer than 28 days in the past.
         *
         * @param date the date to check
         * @return true if the date is valid, false if not
         */
        private fun validateDate(date: ZonedDateTime): Boolean {
            val time = ZonedDateTime.now()
            val diff = time.until(date, ChronoUnit.DAYS)
            return diff <= 28
        }

        /**
         * A utility method to check the format of a date string.
         *
         * @param date the string to check
         * @return true of valid, false if not
         */
        private fun checkStringFormatting(date: String): Boolean {
            return try {
                df.parse(date)
                true
            } catch (e: DateTimeParseException) {
                LOG.warn("Parsing error", e)
                false
            }
        }
    }
}