package com.belzsoftware.futspect.network

import com.belzsoftware.futspect.model.fixture.FixtureSearch
import com.belzsoftware.futspect.model.league.LeagueSearch
import com.belzsoftware.futspect.model.shared.ApiCall
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FootballApiService {

    @GET("leagues")
    suspend fun getLeaguesAsync(): Response<ApiCall<LeagueSearch>>

    @GET("fixtures/league/{id}")
    suspend fun getFixturesForLeague(
        @Path("id") leagueId: Int) : Response<ApiCall<FixtureSearch>>
}