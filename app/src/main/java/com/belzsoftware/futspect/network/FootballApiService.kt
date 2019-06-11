package com.belzsoftware.futspect.network

import com.belzsoftware.futspect.model.league.LeagueSearch
import com.belzsoftware.futspect.model.fixture.FixtureSearch
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FootballApiService {

    @GET("competitions")
    fun getLeaguesAsync(): Deferred<Response<LeagueSearch>>

    @GET("competitions/{id}/matches")
    fun getMatchesForLeagueAsync(
        @Path("id") leagueId: Int,
        @Query("matchDay") matchDay: Int) : Deferred<Response<FixtureSearch>>
}