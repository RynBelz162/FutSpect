package com.belzsoftware.futspect.data.league

import com.belzsoftware.futspect.data.network.FootballApiService
import com.belzsoftware.futspect.util.getResult
import javax.inject.Inject

class LeaguesRemoteSource @Inject constructor(private val service: FootballApiService) {

    suspend fun fetchLeagues() =
        getResult { service.getLeaguesAsync() }

    suspend fun fetchTable(leagueId: Int) =
        getResult { service.getStandingsForLeagueAsync(2019, leagueId) }
}