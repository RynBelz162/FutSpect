package com.belzsoftware.futspect.network

import com.belzsoftware.futspect.util.futspectFbApiKey
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FootballRetrofitFactory {

    private const val baseUrl: String = "http://api.football-data.org/v2/x"

    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("X-Auth-Token", futspectFbApiKey)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val fbClient = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .build()

    fun createFootballApiService(): FootballApiService {

        return Retrofit.Builder()
            .client(fbClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(FootballApiService::class.java)
    }
}