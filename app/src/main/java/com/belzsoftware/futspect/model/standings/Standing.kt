package com.belzsoftware.futspect.model.standings

import com.belzsoftware.futspect.model.team.Team
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class Standing(
    val rank: Int,
    val team: Team,
    val points: Int,
    val goalDiff: Int?,
    val group: String,
    val form: String,
    val status: String?,
    val description: String?,
    val all: MatchesPlayed,
    val home: MatchesPlayed,
    val away: MatchesPlayed,
    val update: Date
)