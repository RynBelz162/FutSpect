package com.belzsoftware.futspect.ui.tables

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belzsoftware.futspect.network.FootballRetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TableViewModel : ViewModel() {
    init {
        initialLoad()
    }

    val leagueName: String = "Premier league yo"

    private fun initialLoad() {
        viewModelScope.launch {
            fetchCompetitions()
        }
    }

    suspend fun fetchCompetitions()  = withContext(Dispatchers.Default) {
        val result = FootballRetrofitFactory
            .createFootballApiService()
            .getCompetitions().await()
    }
}