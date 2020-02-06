package com.belzsoftware.futspect.ui.leagues.table

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.belzsoftware.futspect.data.league.LeaguesRepository
import com.belzsoftware.futspect.model.shared.ApiCall
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.model.standings.StandingsSearch
import com.belzsoftware.futspect.util.defaultResult
import javax.inject.Inject

class TableViewModel @Inject constructor(
    private val leaguesRepository: LeaguesRepository
) : ViewModel() {

    private val _leagueId = MutableLiveData<Int>()

    private val _standings = MediatorLiveData<Result<ApiCall<StandingsSearch>>>()
    val standings: LiveData<Result<ApiCall<StandingsSearch>>> = _standings

    fun setLeagueId(leagueId: Int) {
        _leagueId.value = leagueId

        _standings.addSource(loadStandings()) { value ->
            _standings.value = value
        }
    }

    private fun loadStandings(): LiveData<Result<ApiCall<StandingsSearch>>> {
        val leagueId = _leagueId.value ?: return defaultResult()
        return leaguesRepository.getTable(leagueId)
    }
}