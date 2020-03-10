package com.belzsoftware.futspect.model.status

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Status(
    @Json(name = "long") val longStatus: String,
    @Json(name = "short") val shortStatus: String,
    val elapsed: Int?
)