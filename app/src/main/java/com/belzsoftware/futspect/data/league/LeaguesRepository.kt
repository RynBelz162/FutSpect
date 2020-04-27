package com.belzsoftware.futspect.data.league

import com.belzsoftware.futspect.entity.league.LeagueFilters
import com.belzsoftware.futspect.util.resultLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeaguesRepository @Inject constructor(
    private val remoteSource: LeaguesRemoteSource,
    private val leaguesDao: LeaguesDao
) {

    fun filterLeaguesAsync(search: String?) =
        resultLiveData { remoteSource.filterLeagues(search) }

    fun getTable(leagueId: Int) =
        resultLiveData { remoteSource.fetchTable(leagueId) }

    fun getFilters() = leaguesDao.getLeagueFilters()

    suspend fun saveFilters(filters: LeagueFilters) = leaguesDao.insert(filters)
}