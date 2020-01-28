package com.belzsoftware.futspect.model.standings

import com.google.gson.annotations.SerializedName

data class MatchesPlayed (
    @SerializedName("matchsPlayed") val matchesPlayed: Int,
    val win: Int,
    val draw: Int,
    val lose: Int,
    val goalsFor: Int,
    val goalsAgainst: Int
)