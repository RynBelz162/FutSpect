package com.belzsoftware.futspect.ui.tables

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belzsoftware.futspect.model.league.League
import com.belzsoftware.futspect.network.FootballApiService
import kotlinx.coroutines.launch
import javax.inject.Inject

class TableViewModel @Inject constructor(
    private val footballApiService: FootballApiService
): ViewModel() {

    val leagues = MutableLiveData<List<League>>()

    init {
        initialLoad()
    }

    private fun initialLoad() {
        viewModelScope.launch {
            val result = footballApiService.getLeaguesAsync()


            val leagueList =
                result.body()?.leagues
                    ?: emptyList()

            leagues.value = leagueList
                .sortedWith(compareBy({ it.area.id }, { it.id }))
        }
    }
}