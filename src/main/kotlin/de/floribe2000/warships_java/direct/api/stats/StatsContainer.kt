package de.floribe2000.warships_java.direct.api.stats

class StatsContainer : BaseStatsContainer() {
    /**
     * max frags in a battle
     */
    val max_frags_battle = 0

    /**
     * total draws
     */
    val draws = 0

    /**
     * max xp in a battle
     */
    val max_xp = 0

    /**
     * total wins
     */
    override val wins = 0

    /**
     * total planes killed
     */
    val planes_killed = 0

    /**
     * total losses
     */
    override val losses = 0

    /**
     * torpedo stats
     */
    val torpedoes: ExtendedWeaponStatsWithShip? = null

    /**
     * total battles
     */
    override val battles = 0

    /**
     * max damage in a battle
     */
    val max_damage_dealt = 0

    /**
     * total damage
     */
    val damage_dealt = 0

    /**
     * max plane kills in a battle
     */
    val max_planes_killed = 0

    /**
     * aircraft stats
     */
    val aircraft: WeaponStatsWithShip? = null

    /**
     * ramming stats
     */
    val ramming: WeaponStatsWithShip? = null

    /**
     * main battery stats
     */
    val main_battery: ExtendedWeaponStatsWithShip? = null

    /**
     * secondary battery stats
     */
    val secondary_battery: ExtendedWeaponStatsWithShip? = null

    /**
     * total survived wins
     */
    override val survived_wins = 0

    /**
     * total frags
     */
    val frags = 0

    /**
     * gained xp
     */
    override val xp: Long = 0

    /**
     * total survived battles
     */
    override val survived_battles = 0
}