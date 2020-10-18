package de.floribe2000.warships_java.direct.api.typeDefinitions;

/**
 * The languages for the api that are also supported by this library.
 * The default language is always english, this language doesn't have to be specified.
 */
public enum Language {
    CZECH("cs"),
    GERMAN("de"),
    ENGLISH("en"), //default language
    JAPANESE("ja"),
    POLISH("pl"),
    RUSSIAN("ru");

    /**
     * The language code of the language
     */
    private String code;

    private Language(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
