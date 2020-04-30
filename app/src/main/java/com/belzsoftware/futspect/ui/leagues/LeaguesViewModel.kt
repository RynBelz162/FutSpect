package com.belzsoftware.futspect.ui.leagues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.belzsoftware.futspect.data.league.LeaguesRepository
import javax.inject.Inject

class LeaguesViewModel @Inject constructor(
    leaguesRepository: LeaguesRepository
) : ViewModel() {

    val leagues = leaguesRepository.filterLeaguesAsync()
        .asLiveData(viewModelScope.coroutineContext)
}