package de.floribe2000.warships_java.account;

import de.floribe2000.warships_java.api.IRequestAction;
import de.floribe2000.warships_java.api.Region;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerStatisticsByDateRequest implements IRequestAction<PlayersStatisticsByDate>, IAccountRequest<PlayerStatisticsByDateRequest> {

    /**
     * A Logger instance used to log events of this class
     */
    private final Logger LOG = LoggerFactory.getLogger(getClass().getSimpleName());

    private static final String DATE_FORMAT = "yyyyMMdd";

    private final static DateFormat df = new SimpleDateFormat(DATE_FORMAT);

    /**
     * The server region for this request
     */
    private Region region = null;

    private int accountId = 0;

    private List<String> dates = new ArrayList<>();

    public PlayerStatisticsByDateRequest createRequest() {
        return new PlayerStatisticsByDateRequest();
    }

    @Override
    public PlayerStatisticsByDateRequest region(Region region) {
        this.region = region;
        return this;
    }

    public PlayerStatisticsByDateRequest accountId(int accountId) {
        this.accountId = accountId;
        return this;
    }

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

    @Override
    public PlayersStatisticsByDate fetch() {
        if (region == null || accountId == 0) {
            throw new IllegalArgumentException("Region must not be null and accountId has to be defined.");
        }
        String path = "/wows/account/statsbydate/";
        return null;
    }

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

    private static boolean validateDate(Date date) {
        Date now = new Date();
        long diff = now.getTime() - date.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) <= 28;
    }

    private static boolean checkStringFormatting(String date) {
        try {
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
