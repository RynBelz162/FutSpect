package com.belzsoftware.futspect.di

import com.belzsoftware.futspect.data.fixtures.FixturesRemoteSource
import com.belzsoftware.futspect.data.league.LeaguesRemoteSource
import com.belzsoftware.futspect.data.network.FootballApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providesLeaguesRemoteSource(footballApiService: FootballApiService) =
        LeaguesRemoteSource(footballApiService)

    @Provides
    @Singleton
    fun providesFixturesRemoteSource(footballApiService: FootballApiService) =
        FixturesRemoteSource(footballApiService)
}