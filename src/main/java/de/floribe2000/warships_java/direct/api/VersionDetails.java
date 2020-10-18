package de.floribe2000.warships_java.direct.api;

public class VersionDetails {

    private final int first;

    private final int second;

    private final int third;

    public VersionDetails(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public String toString() {
        return first + "." + second + "." + third;
    }
}
