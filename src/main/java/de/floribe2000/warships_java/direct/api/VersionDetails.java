package de.floribe2000.warships_java.direct.api;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VersionDetails {

    private final int first;

    private final int second;

    private final int third;

    @Override
    public String toString() {
        return first + "." + second + "." + third;
    }
}
