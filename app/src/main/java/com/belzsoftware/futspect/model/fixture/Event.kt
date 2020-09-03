package com.belzsoftware.futspect.model.fixture

import com.belzsoftware.futspect.model.status.Time
import com.belzsoftware.futspect.model.team.Player
import com.belzsoftware.futspect.model.team.Team
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Event(
    val time: Time,
    val team: Team,
    val player: Player,
    val assist: Player,
    val type: String,
    val detail: String,
    val comments: String?
)
