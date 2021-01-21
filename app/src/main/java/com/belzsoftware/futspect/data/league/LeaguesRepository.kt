package com.belzsoftware.futspect.data.league

import com.belzsoftware.futspect.entity.league.LeagueEntity
import com.belzsoftware.futspect.entity.league.LeagueFilters
import com.belzsoftware.futspect.entity.league.LeaguePreference
import com.belzsoftware.futspect.model.country.Country
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
                    val filteredCache = filterCachedLeagues(filters?.searchTerm, cachedLeagues)
                    val response = mapToResponse(filteredCache)
                    emit(groupedLeagueResult(response))
                    return@collect
                }

                val result = remoteSource.filterLeagues(filters?.searchTerm)
                if (result is Result.Success) {
                    cacheAllLeagues(result, filters)
                    emit(groupedLeagueResult(result.data.response))
                    return@collect
                }

                emit(Result.Error<HashMap<Country, List<LeagueResponse>>>("There was an error trying to retrieve the leagues. Please try again."))
            }

    }.flowOn(Dispatchers.IO)

    fun getTable(leagueId: Int) =
        resultLiveData { remoteSource.fetchTable(leagueId) }

    fun getFilters() = leaguesDao.getLeagueFilters()

    suspend fun saveFilters(filters: LeagueFilters) = leaguesDao.insertFilters(filters)

    suspend fun saveLeaguePreferences(ids: List<Int>) {
        val leaguePreferences = ids.map {
            LeaguePreference(it)
        }

        leaguesDao.insertLeaguePreferences(leaguePreferences)
    }

    fun getLeaguePreferences() = leaguesDao.getLeaguePreferences()

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

    private fun mapToResponse(entities: List<LeagueEntity>): List<LeagueResponse> {
        return entities.map { entity ->
                LeagueResponse(entity.map(), entity.mapToCountry(), emptyList())
            }
    }

    private fun filterCachedLeagues(searchTerm: String?, leagues: List<LeagueEntity>): List<LeagueEntity> {
        val nonNullSearchTerm = searchTerm ?: return leagues

        return leagues.filter {
            it.name.contains(nonNullSearchTerm) || it.country.contains(nonNullSearchTerm)
        }
    }

    private fun groupedLeagueResult(leagues: List<LeagueResponse>): Result<HashMap<Country, List<LeagueResponse>>> {
        val groups = leagues.groupBy {
            it.country
        } as HashMap<Country, List<LeagueResponse>>

        return Result.Success(groups)
    }
}