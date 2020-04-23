package com.belzsoftware.futspect.ui.leagues

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.belzsoftware.futspect.data.league.LeaguesRepository
import com.belzsoftware.futspect.model.league.LeagueResponse
import com.belzsoftware.futspect.model.shared.ApiCall
import com.belzsoftware.futspect.model.shared.Result
import javax.inject.Inject

class LeaguesViewModel @Inject constructor(
    leaguesRepository: LeaguesRepository
) : ViewModel() {
    val leagues: LiveData<Result<ApiCall<List<LeagueResponse>>>> = leaguesRepository.getLeagues()
}