package com.belzsoftware.futspect.model.fixture

import com.belzsoftware.futspect.model.team.Team
import java.util.*

data class Fixture(
    val id: Int,
    val utcDate: Date,
    val matchDay: Int,
    val attendance: Int,
    val homeTeam: Team,
    val awayTeam: Team
)