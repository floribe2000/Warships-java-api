package de.floribe2000.warships_java.clans;

import de.floribe2000.warships_java.api.IRequestAction;
import de.floribe2000.warships_java.api.IResponseFields;
import de.floribe2000.warships_java.api.Language;
import de.floribe2000.warships_java.api.Region;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//TODO
public class ClanDetailsRequest implements IRequestAction<ClanDetails>, IClanRequest<ClanDetailsRequest> {

    /**
     * A Logger instance used to log events of this class
     */
    private final Logger LOG = LoggerFactory.getLogger(getClass().getSimpleName());

    /**
     * The server region for this request
     */
    private Region region = null;

    /**
     * The language for the api response
     */
    private Language language = null;

    private Set<Integer> clanIds = new HashSet<>();

    private boolean extra = false;

    @Override
    public ClanDetailsRequest region(Region region) {
        this.region = region;
        return this;
    }

    @Override
    public ClanDetailsRequest language(Language language) {
        this.language = language;
        return this;
    }

    public ClanDetailsRequest extra(ExtraField field) {
        extra = field != null; //Since there is only one field, there is no need to store that field
        return this;
    }

    public ClanDetailsRequest clanIds(Collection<Integer> clanIds) {
        if (clanIds == null || clanIds.size() > 100 || clanIds.size() < 1) {
            throw new IllegalArgumentException("The collection must not be null and have between 1 and 100 entries")
        }
        this.clanIds = new HashSet<>(clanIds);
    }

    @Override
    public ClanDetails fetch() {
        return null;
    }

    @AllArgsConstructor
    public enum ExtraField implements IResponseFields {
        MEMBERS("members");

        private String key;

        @Override
        public String retrieveKey() {
            return key;
        }
    }
}
