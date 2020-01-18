package com.belzsoftware.futspect.ui.tables

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belzsoftware.futspect.model.league.League
import com.belzsoftware.futspect.network.FootballApiService
import kotlinx.coroutines.launch
import javax.inject.Inject

class TableViewModel @Inject constructor(
    private val footballApiService: FootballApiService
) : ViewModel() {

    private val _leagues = MutableLiveData<List<League>>()
    val leagues: LiveData<List<League>> = _leagues

    init {
        initialLoad()
    }

    private fun initialLoad() {
        viewModelScope.launch {
            val result = footballApiService.getLeaguesAsync()


            val leagueList =
                result.body()?.api?.leagues
                    ?: emptyList()

            _leagues.value = leagueList
                .sortedWith(compareBy({ it.countryCode }, { it.id }))
        }
    }
}