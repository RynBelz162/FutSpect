package com.belzsoftware.futspect.data.league

import com.belzsoftware.futspect.entity.league.LeagueEntity
import com.belzsoftware.futspect.entity.league.LeagueFilters
import com.belzsoftware.futspect.model.league.LeagueResponse
import com.belzsoftware.futspect.model.shared.ApiCall
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.util.extensions.resultLiveData
import com.belzsoftware.futspect.util.mappers.map
import com.belzsoftware.futspect.util.mappers.mapToCountry
import com.belzsoftware.futspect.util.mappers.reverseMap
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
            .collect { filters ->

                val cachedLeagues = leaguesDao.getLeagues()

                if (cachedLeagues.isNotEmpty()) {
                    val response = mapToResponse(cachedLeagues)
                    emit(Result.Success(response))
                    return@collect
                }

                val result = remoteSource.filterLeagues(filters?.searchTerm)
                cacheAllLeagues(result, filters)
                emit(result)
            }

    }.flowOn(Dispatchers.IO)

    fun getTable(leagueId: Int) =
        resultLiveData { remoteSource.fetchTable(leagueId) }

    fun getFilters() = leaguesDao.getLeagueFilters()

    suspend fun saveFilters(filters: LeagueFilters) = leaguesDao.insertFilters(filters)

    private suspend fun cacheAllLeagues(
        result: Result<ApiCall<List<LeagueResponse>>>,
        filters: LeagueFilters?
    ) {

        if (!filters?.searchTerm.isNullOrEmpty() || result !is Result.Success) {
            return
        }

        val entities = result.data.response.map {
            it.reverseMap()
        }

        leaguesDao.insertLeagues(entities)
    }

    private fun mapToResponse(entities: List<LeagueEntity>): ApiCall<List<LeagueResponse>> {
        val responses =
            entities.map { entity ->
                LeagueResponse(entity.map(), entity.mapToCountry(), emptyList())
            }

        return ApiCall(responses.count(), responses)
    }
}