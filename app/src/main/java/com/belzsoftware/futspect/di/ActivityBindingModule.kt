package com.belzsoftware.futspect.di

import com.belzsoftware.futspect.ui.MainActivity
import com.belzsoftware.futspect.ui.fixtures.FixturesModule
import com.belzsoftware.futspect.ui.leagues.LeaguesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            LeaguesModule::class,
            FixturesModule::class
        ]
    )
    abstract fun mainActivity() : MainActivity
}