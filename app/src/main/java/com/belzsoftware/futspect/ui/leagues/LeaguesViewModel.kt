package com.belzsoftware.futspect.ui.leagues

import androidx.lifecycle.*
import com.belzsoftware.futspect.data.league.LeaguesRepository
import com.belzsoftware.futspect.model.league.LeagueResponse
import com.belzsoftware.futspect.model.shared.ApiCall
import com.belzsoftware.futspect.model.shared.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LeaguesViewModel @Inject constructor(
    private val leaguesRepository: LeaguesRepository
) : ViewModel() {

    private val _leagues = MediatorLiveData<Result<ApiCall<List<LeagueResponse>>>>()
    val leagues: LiveData<Result<ApiCall<List<LeagueResponse>>>>
        get() = _leagues

    init {
        getFilters()
    }

    private fun getFilters() {
        viewModelScope.launch {
            val filters = leaguesRepository
                .getFilters()
                .asLiveData(
                    Dispatchers.IO + viewModelScope.coroutineContext
                )

            // TODO: this works but is stupid
            _leagues.addSource(filters) {
                _leagues.addSource(getLeagues(filters.value?.searchTerm)) {
                    _leagues.value = it
                }
            }
        }
    }

    private fun getLeagues(search: String?): LiveData<Result<ApiCall<List<LeagueResponse>>>> {
        return leaguesRepository.filterLeaguesAsync(search)
    }
}