package com.belzsoftware.futspect.data.team

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamRepository @Inject constructor(
    private val teamRemoteSource: TeamRemoteSource
) {

    fun getTeamStatistics(teamId: Int, leagueId: Int) = flow {
        emit(teamRemoteSource.getTeamStatistics(teamId, leagueId))
    }.flowOn(Dispatchers.IO)
}