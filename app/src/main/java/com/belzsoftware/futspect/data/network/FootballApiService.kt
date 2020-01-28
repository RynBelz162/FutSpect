package com.belzsoftware.futspect.data.network

import com.belzsoftware.futspect.model.fixture.FixtureSearch
import com.belzsoftware.futspect.model.league.LeagueSearch
import com.belzsoftware.futspect.model.shared.ApiCall
import com.belzsoftware.futspect.model.standings.StandingsSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FootballApiService {

    @GET("leagues")
    suspend fun getLeaguesAsync(): Response<ApiCall<LeagueSearch>>

    @GET("fixtures/league/{id}")
    suspend fun getFixturesForLeague(
        @Path("id") leagueId: Int
    ): Response<ApiCall<FixtureSearch>>

    @GET("standings")
    suspend fun getStandingsForLeagueAndSeason(
        @Query("league") leagueId: Int,
        @Query("season") seasonYear: String
    ): Response<ApiCall<StandingsSearch>>
}