package de.floribe2000.warships_java.direct.encyclopedia;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;

import java.util.Map;

public class Achievments {

    private Status status = Status.ERROR;

    /**
     * Details about errors in case of a failed request.
     * <p>Field is null if no errors occurred during the request!</p>
     */
    private ErrorContainer error = null;

    private Meta meta = null;

    private DataEntry data = null;

    public Status getStatus() {
        return this.status;
    }

    public ErrorContainer getError() {
        return this.error;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public DataEntry getData() {
        return this.data;
    }

    public static class DataEntry {

        private Map<String, AchievmentSingle> battle = null;

        public Map<String, AchievmentSingle> getBattle() {
            return this.battle;
        }
    }

    public static class AchievmentSingle {
        private String name;

        public String getName() {
            return this.name;
        }
    }

}
