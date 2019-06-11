package com.belzsoftware.futspect.model.league

import com.google.gson.annotations.SerializedName

data class LeagueSearch(
    val count: Int,
    @SerializedName("competitions") val leagues: List<League>
)