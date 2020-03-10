package com.belzsoftware.futspect.model.standings

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MatchesPlayed (
    val played: Int,
    val win: Int,
    val draw: Int,
    val lose: Int
)