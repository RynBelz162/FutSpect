package com.belzsoftware.futspect.network

import com.belzsoftware.futspect.model.league.LeagueSearch
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface FootballApiService {

    @GET("competitions")
    fun getLeaguesAsync(): Deferred<Response<LeagueSearch>>
}