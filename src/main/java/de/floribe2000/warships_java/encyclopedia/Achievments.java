package de.floribe2000.warships_java.encyclopedia;

import de.floribe2000.warships_java.api.Meta;
import lombok.Getter;

import java.util.Map;

@Getter
public class Achievments {

    private String status = "error";

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
