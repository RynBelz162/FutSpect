package com.belzsoftware.futspect.ui.tables

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belzsoftware.futspect.model.league.League
import com.belzsoftware.futspect.network.FootballRetrofitFactory
import kotlinx.coroutines.launch

class TableViewModel : ViewModel() {

    val leagues = MutableLiveData<List<League>>()

    init {
        initialLoad()
    }

    private fun initialLoad() {
        viewModelScope.launch {
            val result = FootballRetrofitFactory
                .createFootballApiService()
                .getLeaguesAsync()
                .await()

            leagues.value =
                result.body()?.competitions
                    ?: emptyList()
        }
    }
}