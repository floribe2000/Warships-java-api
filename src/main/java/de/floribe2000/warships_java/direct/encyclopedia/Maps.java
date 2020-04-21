package de.floribe2000.warships_java.direct.encyclopedia;

import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;
import lombok.Getter;

import java.util.Map;

/**
 * This class is a representation of the result of an encyclopedia maps request.
 *
 * @author floribe2000
 * @since 0.2.13
 */
@Getter
public class Maps implements IApiResponse {

    private Status status = Status.ERROR;

    private Meta meta = null;

    private Map<String, MapEntry> data = null;

    @Getter
    public static class MapEntry {

        private String description = null;

        private String icon = null;

        private int battle_arena_id = 0;

        private String name = null;
    }

    @Override
    public String toString() {
        return IRequestAction.GSON.toJson(this);
    }
}
