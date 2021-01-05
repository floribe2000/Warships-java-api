package de.floribe2000.warships_java.direct.account

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PrivatePlayerData(
    @SerialName("wows_premium_expires_at")
    val wowsPremiumExpirationTime: Long = 0,

    val gold: Int = 0,

    @SerialName("free_xp")
    val freeXp: Int = 0,

    val credits: Int = 0,

    @SerialName("premium_expires_at")
    val wgPremiumExpirationTime: Long = 0,

    @SerialName("empty_slots")
    val emptySlots: Int = 0,

    val slots: Int = 0,

    @SerialName("battle_life_time")
    val battleLifeTime: Long = 0,
)
