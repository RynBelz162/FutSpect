package com.belzsoftware.futspect.data.team

import com.belzsoftware.futspect.data.network.FootballApiService
import com.belzsoftware.futspect.util.extensions.getResult
import javax.inject.Inject

class TeamRemoteSource @Inject constructor(
    private val service: FootballApiService
) {

    suspend fun getTeamStatistics(teamId: Int, leagueId: Int) =
        getResult { service.getTeamStatisticsAsync(teamId, "2020", leagueId) }
}