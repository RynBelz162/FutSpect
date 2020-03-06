package com.belzsoftware.futspect.model.fixture

import com.belzsoftware.futspect.model.status.Status
import java.util.*

data class Fixture(
    val id: Int,
    val venue: String,
    val referee: String,
    val timezone: String,
    val date: Date,
    val timestamp: Long,
    val status: Status
)