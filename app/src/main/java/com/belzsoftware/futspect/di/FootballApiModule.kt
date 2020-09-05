package com.belzsoftware.futspect.di

import com.belzsoftware.futspect.data.network.FootballApiService
import com.belzsoftware.futspect.util.BASE_FOOTBALL_URL
import com.belzsoftware.futspect.util.FUTSPECT_API_KEY
import com.belzsoftware.futspect.util.adapters.LocalDateAdapter
import com.belzsoftware.futspect.util.adapters.LocalDateTimeAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
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
            .add(LocalDateAdapter())
            .add(LocalDateTimeAdapter())
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