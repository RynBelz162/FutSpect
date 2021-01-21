package com.belzsoftware.futspect.ui.leagues.preferences

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.belzsoftware.futspect.data.league.LeaguesRepository

class LeaguePreferencesViewModel @ViewModelInject constructor(
    leaguesRepository: LeaguesRepository
) : ViewModel() {

    val leagues = leaguesRepository.filterLeaguesAsync()
        .asLiveData(viewModelScope.coroutineContext)
}