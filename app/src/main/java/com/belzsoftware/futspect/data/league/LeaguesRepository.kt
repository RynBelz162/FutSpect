package com.belzsoftware.futspect.data.league

import com.belzsoftware.futspect.util.resultLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeaguesRepository @Inject constructor(private val remoteSource: LeaguesRemoteSource) {

    fun getLeagues() =
        resultLiveData { remoteSource.fetchLeagues() }

    fun getTable(leagueId: Int) =
        resultLiveData { remoteSource.fetchTable(leagueId) }
}