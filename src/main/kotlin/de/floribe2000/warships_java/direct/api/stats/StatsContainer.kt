package de.floribe2000.warships_java.direct.api.stats

data class StatsContainer(
    override val wins: Int,
    override val losses: Int,
    override val battles: Int,
    override val survived_wins: Int,
    override val survived_battles: Int,
    override val xp: Long,

    /**
     * max frags in a battle
     */
    val max_frags_battle: Int = 0,

    /**
     * total draws
     */
    val draws: Int = 0,

    /**
     * max xp in a battle
     */
    val max_xp: Int = 0,

    /**
     * total planes killed
     */
    val planes_killed: Int = 0,

    /**
     * torpedo stats
     */
    val torpedoes: ExtendedWeaponStatsWithShip? = null,

    /**
     * max damage in a battle
     */
    val max_damage_dealt: Int = 0,

    /**
     * total damage
     */
    val damage_dealt: Long = 0,

    /**
     * max plane kills in a battle
     */
    val max_planes_killed: Int = 0,

    /**
     * aircraft stats
     */
    val aircraft: WeaponStatsWithShip? = null,

    /**
     * ramming stats
     */
    val ramming: WeaponStatsWithShip? = null,

    /**
     * main battery stats
     */
    val main_battery: ExtendedWeaponStatsWithShip? = null,

    /**
     * secondary battery stats
     */
    val secondary_battery: ExtendedWeaponStatsWithShip? = null,


    /**
     * total frags
     */
    val frags: Int = 0,

    ) : BaseStatsContainer