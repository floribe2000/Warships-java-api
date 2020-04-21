package de.floribe2000.warships_java.direct.encyclopedia;

import de.floribe2000.warships_java.direct.api.*;
import de.floribe2000.warships_java.direct.api.typeDefinitions.ConsumableType;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;
import lombok.Getter;

import java.util.Map;

/**
 * A representation of the api response for a {@link ConsumablesRequest}.
 *
 * @author floribe2000
 * @since 0.2.12
 */
@Getter
public class Consumables implements IApiResponse {

    private Status status = Status.ERROR;

    private Meta meta = null;

    private Map<String, Consumable> data = null;

    @Getter
    public static class Consumable {

        private Map<String, EntryStats> profile = null;

        @Getter
        public static class EntryStats {

            private String description = null;

            private double value = 0;
        }

        private String name = null;

        private int price_gold = 0;

        private String image = null;

        private long consumable_id = 0;

        private int price_credit = 0;

        private ConsumableType type = null;

        private String description = null;

        @Override
        public String toString() {
            return IRequestAction.GSON.toJson(this);
        }

    }

    @Override
    public String toString() {
        return IRequestAction.GSON.toJson(this);
    }
}
