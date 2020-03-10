package com.belzsoftware.futspect.model.team

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Teams(
    val home: Team,
    val away: Team
)