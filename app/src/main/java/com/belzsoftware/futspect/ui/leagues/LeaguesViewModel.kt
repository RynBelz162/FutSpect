package com.belzsoftware.futspect.ui.leagues

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.belzsoftware.futspect.data.league.LeaguesRepository
import com.belzsoftware.futspect.model.league.LeagueResponse
import com.belzsoftware.futspect.model.shared.ApiCall
import com.belzsoftware.futspect.model.shared.Result
import timber.log.Timber
import javax.inject.Inject

class LeaguesViewModel @Inject constructor(
    leaguesRepository: LeaguesRepository
) : ViewModel() {

    init {
        Timber.d("League View Model created")
    }

    val leagues: LiveData<Result<ApiCall<List<LeagueResponse>>>> = leaguesRepository.getLeagues()

    override fun onCleared() {
        Timber.d("League View model destroyed")
        super.onCleared()
    }
}