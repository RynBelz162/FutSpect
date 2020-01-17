package com.belzsoftware.futspect.model.fixture

import com.belzsoftware.futspect.model.team.Team
import com.google.gson.annotations.SerializedName
import java.util.*

data class Fixture(
    @SerializedName("fixture_id") val id: Int,
    @SerializedName("league_id") val leagueId: Int,
    @SerializedName("event_date") val eventDate: Date,
    val status: String,
    val statusShort: String,
    val homeTeam: Team,
    val awayTeam: Team
)