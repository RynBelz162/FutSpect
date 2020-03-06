package com.belzsoftware.futspect.data.league

import androidx.lifecycle.LiveData
import com.belzsoftware.futspect.model.league.LeagueResponse
import com.belzsoftware.futspect.model.shared.ApiCall
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.model.standings.StandingResponse
import com.belzsoftware.futspect.util.resultLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeaguesRepository @Inject constructor(private val remoteSource: LeaguesRemoteSource) {

    fun getLeagues(): LiveData<Result<ApiCall<List<LeagueResponse>>>> =
        resultLiveData { remoteSource.fetchLeagues() }

    fun getTable(leagueId: Int): LiveData<Result<ApiCall<List<StandingResponse>>>> =
        resultLiveData { remoteSource.fetchTable(leagueId) }
}