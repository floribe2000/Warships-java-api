package de.floribe2000.warships_java.direct.encyclopedia;

import de.floribe2000.warships_java.direct.api.ErrorContainer;
import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.typeDefinitions.ConsumableType;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;

import java.util.Map;

/**
 * A representation of the api response for a {@link ConsumablesRequest}.
 *
 * @author floribe2000
 * @since 0.2.12
 */
public class Consumables implements IApiResponse {

    private Status status = Status.ERROR;

    /**
     * Details about errors in case of a failed request.
     * <p>Field is null if no errors occurred during the request!</p>
     */
    private ErrorContainer error = null;

    private Meta meta = null;

    private Map<String, Consumable> data = null;

    public Status getStatus() {
        return this.status;
    }

    public ErrorContainer getError() {
        return this.error;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public Map<String, Consumable> getData() {
        return this.data;
    }

    public static class Consumable {

        private Map<String, EntryStats> profile = null;

        public Map<String, EntryStats> getProfile() {
            return this.profile;
        }

        public String getName() {
            return this.name;
        }

        public int getPrice_gold() {
            return this.price_gold;
        }

        public String getImage() {
            return this.image;
        }

        public long getConsumable_id() {
            return this.consumable_id;
        }

        public int getPrice_credit() {
            return this.price_credit;
        }

        public ConsumableType getType() {
            return this.type;
        }

        public String getDescription() {
            return this.description;
        }

        public static class EntryStats {

            private String description = null;

            private double value = 0;

            public String getDescription() {
                return this.description;
            }

            public double getValue() {
                return this.value;
            }
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
