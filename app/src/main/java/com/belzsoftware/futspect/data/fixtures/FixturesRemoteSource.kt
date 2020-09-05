package com.belzsoftware.futspect.data.fixtures

import com.belzsoftware.futspect.data.network.FootballApiService
import com.belzsoftware.futspect.util.extensions.getCurrentDateTimeString
import com.belzsoftware.futspect.util.extensions.getResult
import javax.inject.Inject

class FixturesRemoteSource @Inject constructor(private val service: FootballApiService) {
    suspend fun getFixtures(leagueId: Int) = getResult {
        service.getFixturesForLeagueAndDateAsync(
            //leagueId,
            getCurrentDateTimeString("YYYY-MM-dd"),
            2019
        )
    }

    suspend fun getFixtureEvents(fixtureId: Int) = getResult {
        service.getFixtureEventsAsync(fixtureId)
    }
}