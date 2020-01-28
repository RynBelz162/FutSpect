package com.belzsoftware.futspect.ui.leagues

import androidx.lifecycle.ViewModel
import com.belzsoftware.futspect.di.ViewModelKey
import com.belzsoftware.futspect.ui.leagues.table.TableFragment
import com.belzsoftware.futspect.ui.leagues.table.TableViewModel
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
    abstract fun bindLeaguesViewModel(viewModel: LeaguesViewModel) : ViewModel

    @ContributesAndroidInjector
    abstract fun contributesTableFragment() : TableFragment

    @Binds
    @IntoMap
    @ViewModelKey(TableViewModel::class)
    abstract fun bindTableViewModel(viewModel: TableViewModel) : ViewModel
}
