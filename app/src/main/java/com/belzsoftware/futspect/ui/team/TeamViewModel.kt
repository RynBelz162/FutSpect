package com.belzsoftware.futspect.ui.team

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.belzsoftware.futspect.data.team.TeamRepository
import com.belzsoftware.futspect.model.shared.ApiCall
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.model.team.TeamStatisticResponse
import com.belzsoftware.futspect.util.extensions.defaultResult

class TeamViewModel @ViewModelInject constructor(
    private val teamRepository: TeamRepository
) : ViewModel() {

    private val _leagueId = MutableLiveData<Int>()
    private val _teamId = MutableLiveData<Int>()

    private val _teamStatsPromise = MediatorLiveData<Result<ApiCall<TeamStatisticResponse>>>()
    val teamStatsPromise: LiveData<Result<ApiCall<TeamStatisticResponse>>> = _teamStatsPromise

    private val _stats = MutableLiveData<TeamStatisticResponse>()
    val stats: LiveData<TeamStatisticResponse> = _stats

    fun setParameters(leagueId: Int, teamId: Int) {

        // set parameters
        _leagueId.value = leagueId
        _teamId.value = teamId

        // load statistic info
        _teamStatsPromise.addSource(loadTeamInfo()) { result ->

            _teamStatsPromise.value = result

            if (result !is Result.Success){
                return@addSource
            }

            _stats.value = result.data.response
        }
    }

    private fun loadTeamInfo(): LiveData<Result<ApiCall<TeamStatisticResponse>>> {
        val leagueId = _leagueId.value ?: return defaultResult()
        val teamId = _teamId.value ?: return defaultResult()

        return teamRepository.getTeamStatistics(teamId, leagueId)
            .asLiveData(viewModelScope.coroutineContext)
    }
}