package com.belzsoftware.futspect.model.status

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Goals(
    val home: Int? = 0,
    val away: Int? = 0
)