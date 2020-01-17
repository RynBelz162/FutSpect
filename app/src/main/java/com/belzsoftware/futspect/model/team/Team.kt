package com.belzsoftware.futspect.model.team

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("team_id") val id: Int,
    @SerializedName("team_name") val name: String,
    val logo: String
)