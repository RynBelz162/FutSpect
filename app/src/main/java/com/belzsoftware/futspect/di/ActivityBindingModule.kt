package com.belzsoftware.futspect.di

import com.belzsoftware.futspect.ui.MainActivity
import com.belzsoftware.futspect.ui.fixtures.FixturesModule
import com.belzsoftware.futspect.ui.tables.TableModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(
        modules = [
            TableModule::class,
            FixturesModule::class
        ]
    )
    abstract fun mainActivity() : MainActivity
}