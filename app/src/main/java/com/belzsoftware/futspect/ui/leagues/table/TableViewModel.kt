package com.belzsoftware.futspect.ui.leagues.table

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.belzsoftware.futspect.data.league.LeaguesRepository
import com.belzsoftware.futspect.model.league.League
import com.belzsoftware.futspect.model.shared.ApiCall
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.model.standings.StandingResponse
import com.belzsoftware.futspect.util.defaultResult
import javax.inject.Inject

class TableViewModel @Inject constructor(
    private val leaguesRepository: LeaguesRepository
) : ViewModel() {

    private val _league = MutableLiveData<League>()
    val league: LiveData<League> = _league

    private val _standings = MediatorLiveData<Result<ApiCall<List<StandingResponse>>>>()
    val standings: LiveData<Result<ApiCall<List<StandingResponse>>>> = _standings

    fun setLeagueId(league: League) {
        _league.value = league

        _standings.addSource(loadStandings()) { value ->
            _standings.value = value
        }
    }

    private fun loadStandings(): LiveData<Result<ApiCall<List<StandingResponse>>>> {
        val leagueId = _league.value?.id ?: return defaultResult()
        return leaguesRepository.getTable(leagueId)
    }
}