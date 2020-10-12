package de.floribe2000.warships_java.direct.encyclopedia;

import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Nation;
import de.floribe2000.warships_java.direct.api.typeDefinitions.ShipType;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Tier;
import lombok.Getter;

/**
 * A reduced view of a {@link de.floribe2000.warships_java.direct.encyclopedia.Warships.ShipEntry ShipEntry}.
 */
@Getter
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
        return IRequestAction.GSON.toJson(this);
    }
}
