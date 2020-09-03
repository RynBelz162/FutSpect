package com.belzsoftware.futspect.model.status

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Time(
    val elapsed: Int,
    val extra: Int?
)