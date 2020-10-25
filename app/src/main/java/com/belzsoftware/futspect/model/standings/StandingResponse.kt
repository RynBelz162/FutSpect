package com.belzsoftware.futspect.model.standings

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StandingResponse(
    val league: StandingLeague
)

@JsonClass(generateAdapter = true)
data class StandingLeague(
    val id: Int,
    val name: String,
    val type: String?,
    val logo: String,
    val country: String,
    val flag: String?,
    val season: Int,
    val standings: List<List<Standing>>
)