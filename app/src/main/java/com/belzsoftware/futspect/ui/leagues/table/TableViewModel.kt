package com.belzsoftware.futspect.ui.leagues.table

import androidx.lifecycle.ViewModel
import com.belzsoftware.futspect.data.league.LeaguesRepository
import javax.inject.Inject

class TableViewModel @Inject constructor(
    leaguesRepository: LeaguesRepository
) : ViewModel() {

    val standings = leaguesRepository.getTable(524, "2019")
}