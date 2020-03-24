package de.floribe2000.warships_java.account;

import de.floribe2000.warships_java.api.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerStatisticsByDateRequest implements IRequestAction<PlayersStatisticsByDate>, IAccountRequest<PlayerStatisticsByDateRequest> {

    /**
     * A Logger instance used to log events of this class
     */
    private final Logger LOG = LoggerFactory.getLogger(getClass().getSimpleName());

    /**
     * The date format pattern used for the date field
     */
    private static final String DATE_FORMAT = "yyyyMMdd";

    /**
     * The {@link DateFormat} instance used to format dates that can be used by the api
     */
    private final static DateFormat df = new SimpleDateFormat(DATE_FORMAT);

    /**
     * The server region for this request
     */
    private Region region = null;

    /**
     * The language for the api response
     */
    private Language language = null;

    /**
     * The account id for this request
     */
    private int accountId = 0;

    /**
     * The list of dates for this request. A maximum of 10 dates is allowed.
     */
    private List<String> dates = new ArrayList<>();

    /**
     * The extra field for this request. Only one field is possible in this case.
     */
    private ExtraField extra = null;

    /**
     * Creates a new empty request instance of this class.
     *
     * @return a new instance of this class
     */
    public PlayerStatisticsByDateRequest createRequest() {
        return new PlayerStatisticsByDateRequest();
    }

    @Override
    public PlayerStatisticsByDateRequest region(Region region) {
        this.region = region;
        return this;
    }

    @Override
    public PlayerStatisticsByDateRequest language(Language language) {
        this.language = language;
        return this;
    }

    /**
     * Sets the account id for this request. An existing id will be replaced.
     *
     * @param accountId the account id for the request
     * @return the instance of this request
     */
    public PlayerStatisticsByDateRequest accountId(int accountId) {
        this.accountId = accountId;
        return this;
    }

    /**
     * Sets the list of dates for this request. A maximum of 10 dates is allowed.
     * <p>Existing dates will be replaced!</p>
     *
     * @param dates the list of dates for this request
     * @return the instance of this request
     * @throws IllegalArgumentException <ul>
     *                                  <li>If the date format of the string doesn't match the pattern yyyyMMdd</li>
     *                                  <li>If the provided collection of dates has more than 10 elements</li>
     *                                  </ul>
     */
    public PlayerStatisticsByDateRequest dates(Collection<String> dates) {
        if (dates.size() <= 10) {
            dates.forEach(d -> {
                if (!checkStringFormatting(d)) {
                    throw new IllegalArgumentException("Dateformat doesn't match the requirements");
                }
            });
            this.dates = new ArrayList<>(dates);
        } else {
            throw new IllegalArgumentException("You can only specify a maximum of 10 dates.");
        }
        return this;
    }

    /**
     * Adds a single date to this request.
     * <p>Existing dates won't be changed!</p>
     *
     * @param date the date to add
     * @return the instance of this request
     * @throws IllegalStateException    If there are already 10 dates set for this request
     * @throws IllegalArgumentException If the date format of the string doesn't match the pattern yyyyMMdd
     */
    public PlayerStatisticsByDateRequest addDate(String date) {
        if (dates.size() >= 10) {
            throw new IllegalStateException("You can't set more than 10 dates.");
        }
        if (checkStringFormatting(date)) {
            dates.add(date);
        } else {
            throw new IllegalArgumentException("The date has to match the pattern yyyyMMdd");
        }
        return this;
    }

    /**
     * Adds a single date to this request.
     * <p>Existing dates won't be changed!</p>
     * <p>This method also checks the validity of the date, it must be between today and 28 days ago.</p>
     *
     * @param date the date to add
     * @return the instance of this request
     * @throws IllegalArgumentException If the date is not valid, for example if it's more than 28 days in the past
     * @throws IllegalStateException    If there are already 10 dates set for this request
     */
    public PlayerStatisticsByDateRequest addDate(Date date) {
        if (!validateDate(date)) {
            throw new IllegalArgumentException("Date is not valid");
        }
        if (dates.size() < 10) {
            dates.add(df.format(date));
        } else {
            throw new IllegalStateException("You can't set more than 10 dates.");
        }
        return this;
    }

    /**
     * Sets the extra field for this request.
     * <p>Replaces existing values!</p>
     * <p>In this case there is only one field available to this method accepts either {@link ExtraField#PVE} or null.</p>
     *
     * @param extra the extra field for this request
     * @return the instance of this request
     */
    public PlayerStatisticsByDateRequest extra(ExtraField extra) {
        this.extra = extra;
        return this;
    }

    /**
     * Executes a request and returns the result of the request.
     * <p>All requests are executed synchronous on this thread. It is safe to execute this in a new thread if it is required to be run asynchronous.</p>
     *
     * @return an instance of {@link PlayersStatisticsByDate} that contains all requested player data. If the request fails, an empty instance is returned.
     * @throws IllegalArgumentException <ul>
     *                                  <li>If this method is called and region is null.</li>
     *                                  <li>If this method is called and no account id has been defined.</li>
     *                                  </ul>
     */
    @Override
    public PlayersStatisticsByDate fetch() {
        if (region == null || accountId == 0) {
            throw new IllegalArgumentException("Region must not be null and accountId has to be defined.");
        }
        String path = "/wows/account/statsbydate/";
        String dates = this.dates.stream().sequential().collect(Collectors.joining(","));
        String url = baseUrl(region, path, language) + FieldType.ACCOUNT_ID + accountId + FieldType.DATES + dates;
        PlayersStatisticsByDate result;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            result = GSON.fromJson(reader, PlayersStatisticsByDate.class);
        } catch (Exception e) {
            LOG.error("Exception", e);
            result = new PlayersStatisticsByDate();
        }
        return result;
    }

    /**
     * A utility method to allow validating a date.
     * <p>Parses the given string to a date and returns the result of the check for this date.</p>
     *
     * @param date the date as string
     * @return true if the date is valid, false if the date is invalid or the string doesn't match the required format
     */
    private static boolean validateDate(String date) {
        if (checkStringFormatting(date)) {
            try {
                return validateDate(df.parse(date));
            } catch (ParseException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * A utility method to validate a date.
     * <p>Checks if the date is not longer than 28 days in the past.</p>
     *
     * @param date the date to check
     * @return true if the date is valid, false if not
     */
    private static boolean validateDate(Date date) {
        Date now = new Date();
        long diff = now.getTime() - date.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) <= 28;
    }

    /**
     * A utility method to check the format of a date string.
     *
     * @param date the string to check
     * @return true of valid, false if not
     */
    private static boolean checkStringFormatting(String date) {
        try {
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * All possible extra fields for this request.
     * <p>These fields are optional fields, the request will also work without them.</p>
     */
    @AllArgsConstructor
    public enum ExtraField implements IResponseFields {
        /**
         * The coop battles mode
         */
        PVE("pve");

        private String key;

        @Override
        public String retrieveKey() {
            return key;
        }
    }
}