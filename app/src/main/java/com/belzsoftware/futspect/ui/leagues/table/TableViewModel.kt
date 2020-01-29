package com.belzsoftware.futspect.ui.leagues.table

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.belzsoftware.futspect.data.league.LeaguesRepository
import com.belzsoftware.futspect.model.shared.ApiCall
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.model.standings.StandingsSearch
import javax.inject.Inject

class TableViewModel @Inject constructor(
    leaguesRepository: LeaguesRepository
) : ViewModel() {

    // TODO: Use passed in league id from nav args
    val standings: LiveData<Result<ApiCall<StandingsSearch>>> =
        leaguesRepository.getTable(524)
}