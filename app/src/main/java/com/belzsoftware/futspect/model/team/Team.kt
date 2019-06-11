package com.belzsoftware.futspect.model.team

data class Team(
    val id: Int,
    val name: String,
    val captain: Player,
    val lineup: List<Player>,
    val bench: List<Player>
)