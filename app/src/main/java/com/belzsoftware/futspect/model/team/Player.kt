package com.belzsoftware.futspect.model.team

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Player(
    val id: Int?,
    val name: String?,
)