package com.belzsoftware.futspect.data.league

import com.belzsoftware.futspect.entity.league.LeagueFilters
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.util.resultLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeaguesRepository @Inject constructor(
    private val remoteSource: LeaguesRemoteSource,
    private val leaguesDao: LeaguesDao
) {

    fun filterLeaguesAsync() = flow {
        emit(Result.Loading())

        // when filters change reload data
        getFilters()
            .collect { value ->
                emit(Result.Loading())
                val result = remoteSource.filterLeagues(value?.searchTerm)
                emit(result)
            }

    }.flowOn(Dispatchers.IO)

    fun getTable(leagueId: Int) =
        resultLiveData { remoteSource.fetchTable(leagueId) }

    fun getFilters() = leaguesDao.getLeagueFilters()

    suspend fun saveFilters(filters: LeagueFilters) = leaguesDao.insert(filters)
}