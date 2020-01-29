package com.belzsoftware.futspect.data.league

import com.belzsoftware.futspect.data.network.FootballApiService
import com.belzsoftware.futspect.model.shared.ApiCall
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.model.standings.StandingsSearch
import com.belzsoftware.futspect.util.getResult
import javax.inject.Inject

class LeaguesRemoteSource @Inject constructor(private val service: FootballApiService) {

    suspend fun fetchLeagues() =
        getResult { service.getLeaguesAsync() }

    suspend fun fetchTable(leagueId: Int): Result<ApiCall<StandingsSearch>> =
        getResult { service.getStandingsForLeagueAsync(leagueId) }
}