package com.belzsoftware.futspect.data.network

import com.belzsoftware.futspect.model.fixture.FixtureSearch
import com.belzsoftware.futspect.model.league.LeagueSearch
import com.belzsoftware.futspect.model.shared.ApiCall
import com.belzsoftware.futspect.model.standings.StandingsSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FootballApiService {

    @GET("leagues")
    suspend fun getLeaguesAsync(): Response<ApiCall<LeagueSearch>>

    @GET("fixtures/league/{id}")
    suspend fun getFixturesForLeagueAsync(
        @Path("id") leagueId: Int
    ): Response<ApiCall<FixtureSearch>>

    @GET("leagueTable/{leagueId}")
    suspend fun getStandingsForLeagueAsync(
        @Path("leagueId") leagueId: Int
    ): Response<ApiCall<StandingsSearch>>
}