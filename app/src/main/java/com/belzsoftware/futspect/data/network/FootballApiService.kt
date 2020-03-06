package com.belzsoftware.futspect.data.network

import com.belzsoftware.futspect.model.fixture.FixtureResponse
import com.belzsoftware.futspect.model.league.LeagueResponse
import com.belzsoftware.futspect.model.shared.ApiCall
import com.belzsoftware.futspect.model.standings.StandingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApiService {

    @GET("leagues")
    suspend fun getLeaguesAsync(): Response<ApiCall<List<LeagueResponse>>>

    @GET("fixtures")
    suspend fun getFixturesForLeagueAndDateAsync(
        //@Query("league") leagueId: Int,
        @Query("date") date: String,
        @Query("season") season: Int
    ): Response<ApiCall<List<FixtureResponse>>>

    @GET("standings")
    suspend fun getStandingsForLeagueAsync(
        @Query("season") season: Int,
        @Query("league") leagueId: Int
    ): Response<ApiCall<List<StandingResponse>>>
}