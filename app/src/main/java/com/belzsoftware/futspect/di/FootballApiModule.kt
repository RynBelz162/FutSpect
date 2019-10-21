package com.belzsoftware.futspect.di

import com.belzsoftware.futspect.network.FootballApiService
import com.belzsoftware.futspect.util.BASE_FOOTBALL_URL
import com.belzsoftware.futspect.util.futspectFbApiKey
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class FootballApiModule {

    @Provides
    @Singleton
    internal fun provideInterceptor()= Interceptor { chain ->
        val newRequest = chain.request()
            .newBuilder()
            .addHeader("X-Auth-Token", futspectFbApiKey)
            .build()

        chain.proceed(newRequest)
    }

    @Provides
    @Singleton
    internal fun provideGson() : Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun providerOkHttpClient(interceptor: Interceptor) : OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    internal fun providerRetrofit(gson: Gson, okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_FOOTBALL_URL)
            .build()
    }

    @Provides
    @Singleton
    internal fun providerFootballApiService(retrofit: Retrofit) : FootballApiService {
        return retrofit.create(FootballApiService::class.java)
    }
}