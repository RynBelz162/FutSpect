package com.belzsoftware.futspect.di

import android.content.Context
import com.belzsoftware.futspect.data.FutspectDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context)
            = FutspectDatabase.getDatabase(appContext)

}