package com.belzsoftware.futspect.di

import com.belzsoftware.futspect.data.network.FootballApiService
import com.belzsoftware.futspect.util.BASE_FOOTBALL_URL
import com.belzsoftware.futspect.util.FUTSPECT_API_KEY
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
class FootballApiModule {

    @Provides
    @Singleton
    internal fun providesInterceptor() = Interceptor { chain ->
        val newRequest = chain.request()
            .newBuilder()
            .addHeader("x-rapidapi-key", FUTSPECT_API_KEY)
            .build()

        chain.proceed(newRequest)
    }

    @Provides
    @Singleton
    internal fun providesMoshi(): Moshi {

        return Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    internal fun providesOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    internal fun providesRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_FOOTBALL_URL)
            .build()
    }

    @Provides
    @Singleton
    internal fun providesFootballApiService(retrofit: Retrofit): FootballApiService {
        return retrofit.create(FootballApiService::class.java)
    }
}