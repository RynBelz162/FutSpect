package com.belzsoftware.futspect.di

import com.belzsoftware.futspect.data.fixtures.FixturesRemoteSource
import com.belzsoftware.futspect.data.league.LeaguesRemoteSource
import com.belzsoftware.futspect.data.network.FootballApiService
import com.belzsoftware.futspect.data.team.TeamRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providesLeaguesRemoteSource(footballApiService: FootballApiService) =
        LeaguesRemoteSource(footballApiService)

    @Provides
    @Singleton
    fun providesFixturesRemoteSource(footballApiService: FootballApiService) =
        FixturesRemoteSource(footballApiService)

    @Provides
    @Singleton
    fun providesTeamsRemoteSource(footballApiService: FootballApiService) =
        TeamRemoteSource(footballApiService)
}