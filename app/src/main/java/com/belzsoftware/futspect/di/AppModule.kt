package com.belzsoftware.futspect.di

import android.content.Context
import com.belzsoftware.futspect.MainApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun providesContext(application: MainApplication) : Context {
        return application.applicationContext
    }
}