package com.belzsoftware.futspect.model.fixture

import com.belzsoftware.futspect.model.league.League
import com.belzsoftware.futspect.model.status.Goals
import com.belzsoftware.futspect.model.status.Score
import com.belzsoftware.futspect.model.team.Teams

data class FixtureResponse(
    val fixture: Fixture,
    val league: League,
    val teams: Teams,
    val goals: Goals,
    val score: Score
)