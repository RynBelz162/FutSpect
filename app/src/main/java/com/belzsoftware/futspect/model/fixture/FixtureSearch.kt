package com.belzsoftware.futspect.model.fixture

import com.belzsoftware.futspect.model.league.League
import com.google.gson.annotations.SerializedName

data class FixtureSearch(
    val count: Int,
    @SerializedName("competition") val league: League,
    @SerializedName("matches") val fixtures: List<Fixture>
)