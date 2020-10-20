package de.floribe2000.warships_java.direct.encyclopedia;

import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Nation;
import de.floribe2000.warships_java.direct.api.typeDefinitions.ShipType;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Tier;

/**
 * A reduced view of a {@link de.floribe2000.warships_java.direct.encyclopedia.Warships.ShipEntry ShipEntry}.
 */
public class ShipEntryReduced {

    private final long shipId;

    private final String name;

    private final Tier tier;

    private final Nation nation;

    private final ShipType type;

    private final boolean demoProfile;

    private final boolean premium;

    private final boolean special;

    private final String shipIdStr;

    public ShipEntryReduced(Warships.ShipEntry fullDetails) {
        this.shipId = fullDetails.getShip_id();
        this.name = fullDetails.getName();
        this.tier = fullDetails.getTier();
        this.nation = fullDetails.getNation();
        this.type = fullDetails.getType();
        this.demoProfile = fullDetails.isHas_demo_profile();
        this.premium = fullDetails.is_premium();
        this.special = fullDetails.is_special();
        this.shipIdStr = fullDetails.getShip_id_str();
    }

    @Override
    public String toString() {
        return IRequestAction.Companion.getGSON().toJson(this);
    }

    public long getShipId() {
        return this.shipId;
    }

    public String getName() {
        return this.name;
    }

    public Tier getTier() {
        return this.tier;
    }

    public Nation getNation() {
        return this.nation;
    }

    public ShipType getType() {
        return this.type;
    }

    public boolean isDemoProfile() {
        return this.demoProfile;
    }

    public boolean isPremium() {
        return this.premium;
    }

    public boolean isSpecial() {
        return this.special;
    }

    public String getShipIdStr() {
        return this.shipIdStr;
    }
}
