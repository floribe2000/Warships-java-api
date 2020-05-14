package de.floribe2000.warships_java.direct.encyclopedia;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;
import lombok.Getter;

import java.util.Map;

@Getter
public class Achievments {

    private Status status = Status.ERROR;

    /**
     * Details about errors in case of a failed request.
     * <p>Field is null if no errors occurred during the request!</p>
     */
    private ErrorContainer error = null;

    private Meta meta = null;

    private DataEntry data = null;

    @Getter
    public static class DataEntry {

        private Map<String, AchievmentSingle> battle = null;
    }

    @Getter
    public static class AchievmentSingle {
        private String name;
    }

}
