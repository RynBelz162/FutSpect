package com.belzsoftware.futspect.model.team

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Team(
    val id: Int,
    val name: String,
    val logo: String
)