package com.belzsoftware.futspect.di

import android.content.Context
import com.belzsoftware.futspect.data.FutspectDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context) = FutspectDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideLeaguesDao(database: FutspectDatabase) = database.getLeaguesDao()
}