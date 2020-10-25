package com.belzsoftware.futspect.model.team

import com.belzsoftware.futspect.model.league.League
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamStatisticResponse(
    val league: League,
    val team: Team,
    val form: String,
)