package com.belzsoftware.futspect.ui.fixtures

import androidx.lifecycle.ViewModel
import com.belzsoftware.futspect.di.FragmentScoped
import com.belzsoftware.futspect.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FixturesModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contrubutesFixturesFragment() : FixturesFragment

    @Binds
    @IntoMap
    @ViewModelKey(FixturesViewModel::class)
    abstract fun bindFixturesViewModel(viewModel: FixturesViewModel) : ViewModel
}