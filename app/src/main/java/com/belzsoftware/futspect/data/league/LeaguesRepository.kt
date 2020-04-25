package com.belzsoftware.futspect.data.league

import com.belzsoftware.futspect.entity.league.LeagueFilters
import com.belzsoftware.futspect.util.resultLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeaguesRepository @Inject constructor(
    private val remoteSource: LeaguesRemoteSource,
    private val leaguesDao: LeaguesDao
) {

    fun getLeagues() =
        resultLiveData { remoteSource.fetchLeagues() }

    fun getTable(leagueId: Int) =
        resultLiveData { remoteSource.fetchTable(leagueId) }

    suspend fun getFilters() = withContext(Dispatchers.IO) {
        leaguesDao.getLeagueFilters()
    }

    suspend fun saveFilters(filters: LeagueFilters) = leaguesDao.insert(filters)
}