package com.belzsoftware.futspect.di

import com.belzsoftware.futspect.data.FutspectDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideLeaguesDao(database: FutspectDatabase) = database.getLeaguesDao()
}