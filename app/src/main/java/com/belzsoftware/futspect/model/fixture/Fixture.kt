package com.belzsoftware.futspect.model.fixture

import com.belzsoftware.futspect.model.status.Status
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class Fixture(
    val id: Int,
    val venue: String?,
    val referee: String?,
    val timezone: String,
    val date: Date,
    val timestamp: Long,
    val status: Status
)