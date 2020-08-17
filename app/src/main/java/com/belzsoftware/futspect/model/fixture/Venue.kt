package com.belzsoftware.futspect.model.fixture

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Venue(
    val id: Int?,
    val name: String,
    val city: String
)