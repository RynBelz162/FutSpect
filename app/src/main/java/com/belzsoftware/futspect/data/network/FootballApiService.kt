package com.belzsoftware.futspect.data.network

import com.belzsoftware.futspect.model.fixture.Event
import com.belzsoftware.futspect.model.fixture.FixtureResponse
import com.belzsoftware.futspect.model.league.LeagueResponse
import com.belzsoftware.futspect.model.shared.ApiCall
import com.belzsoftware.futspect.model.standings.StandingResponse
import com.belzsoftware.futspect.model.team.TeamStatisticResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApiService {

    @GET("leagues")
    suspend fun filterLeaguesAsync(
        @Query("search") search: String?
    ): Response<ApiCall<List<LeagueResponse>>>

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

    @GET("fixtures/events")
    suspend fun getFixtureEventsAsync(
        @Query("fixture") fixtureId: Int
    ) : Response<ApiCall<List<Event>>>

    @GET("teams/statistics")
    suspend fun getTeamStatisticsAsync(
        @Query("team") teamId: Int,
        @Query("season") season: String,
        @Query("league") leagueId: Int,
    ) : Response<ApiCall<TeamStatisticResponse>>
}