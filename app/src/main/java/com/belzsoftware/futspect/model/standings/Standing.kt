package com.belzsoftware.futspect.model.standings

import com.google.gson.annotations.SerializedName

data class Standing(
    val rank: Int,
    @SerializedName("team_id") val teamId: Int,
    val teamName: String,
    val logo: String,
    val group: String,
    val forme: String,
    val status: String,
    val description: String,
    val all: MatchesPlayed,
    val home: MatchesPlayed,
    val away: MatchesPlayed,
    val goalsDiff: Int,
    val points: Int,
    val lastUpdate: String
)