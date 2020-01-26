package com.belzsoftware.futspect.ui.leagues

import androidx.lifecycle.ViewModel
import com.belzsoftware.futspect.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class LeaguesModule {

    @ContributesAndroidInjector
    abstract fun contributesLeaguesFragment() : LeaguesFragment

    @Binds
    @IntoMap
    @ViewModelKey(LeaguesViewModel::class)
    abstract fun bindAgendaViewModel(viewModel: LeaguesViewModel) : ViewModel
}
