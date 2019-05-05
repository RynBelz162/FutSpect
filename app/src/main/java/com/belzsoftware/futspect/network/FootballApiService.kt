package com.belzsoftware.futspect.network

import com.belzsoftware.futspect.model.competitions.CompetitionSearch
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface FootballApiService {

    @GET("competitions")
    fun getCompetitionsAsync(): Deferred<Response<CompetitionSearch>>
}