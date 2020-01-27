package com.belzsoftware.futspect.data.fixtures

import com.belzsoftware.futspect.data.network.FootballApiService
import com.belzsoftware.futspect.util.getResult
import javax.inject.Inject

class FixturesRemoteSource @Inject constructor(private val service: FootballApiService) {
    suspend fun getFixtures(leagueId: Int) = getResult { service.getFixturesForLeague(leagueId) }
}