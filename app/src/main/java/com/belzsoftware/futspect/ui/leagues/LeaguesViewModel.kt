package com.belzsoftware.futspect.ui.leagues

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.belzsoftware.futspect.data.league.LeaguesRepository

class LeaguesViewModel @ViewModelInject constructor(
    leaguesRepository: LeaguesRepository
) : ViewModel() {

    val leagues = leaguesRepository.filterLeaguesAsync()
        .asLiveData(viewModelScope.coroutineContext)
}