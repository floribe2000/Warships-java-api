package de.floribe2000.warships_java.direct.encyclopedia;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;

import java.util.Map;

/**
 * This class is a representation of the result of an encyclopedia maps request.
 *
 * @author floribe2000
 * @since 0.2.13
 */
public class Maps implements IApiResponse {

    private Status status = Status.ERROR;

    /**
     * Details about errors in case of a failed request.
     * <p>Field is null if no errors occurred during the request!</p>
     */
    private ErrorContainer error = null;

    private Meta meta = null;

    private Map<String, MapEntry> data = null;

    public Status getStatus() {
        return this.status;
    }

    public ErrorContainer getError() {
        return this.error;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public Map<String, MapEntry> getData() {
        return this.data;
    }

    public static class MapEntry {

        private String description = null;

        private String icon = null;

        private int battle_arena_id = 0;

        private String name = null;

        public String getDescription() {
            return this.description;
        }

        public String getIcon() {
            return this.icon;
        }

        public int getBattle_arena_id() {
            return this.battle_arena_id;
        }

        public String getName() {
            return this.name;
        }
    }

    @Override
    public String toString() {
        return IRequestAction.Companion.getGSON().toJson(this);
    }
}
