package de.floribe2000.warships_java.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The languages for the api that are also supported by this library.
 * The default language is always english, this language doesn't have to be specified.
 */
@AllArgsConstructor
public enum Language {
    CZECH("cs"),
    GERMAN("de"),
    ENGLISH("en"), //default language
    JAPANESE("ja"),
    POLISH("pl"),
    RUSSIAN("ru");

    @Getter
    private String code;
}
