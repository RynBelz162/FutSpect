package com.belzsoftware.futspect.ui.leagues

import androidx.lifecycle.ViewModel
import com.belzsoftware.futspect.di.FragmentScoped
import com.belzsoftware.futspect.di.ViewModelKey
import com.belzsoftware.futspect.ui.leagues.filter.LeagueFilterBottomSheetModalFragment
import com.belzsoftware.futspect.ui.leagues.filter.LeagueFilterViewModel
import com.belzsoftware.futspect.ui.leagues.table.TableFragment
import com.belzsoftware.futspect.ui.leagues.table.TableViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class LeaguesModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributesLeaguesFragment(): LeaguesFragment

    @Binds
    @IntoMap
    @ViewModelKey(LeaguesViewModel::class)
    abstract fun bindLeaguesViewModel(viewModel: LeaguesViewModel): ViewModel

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributesLeagueFilterBottomSheetFragment(): LeagueFilterBottomSheetModalFragment

    @Binds
    @IntoMap
    @ViewModelKey(LeagueFilterViewModel::class)
    abstract fun bindLeagueFilterViewModel(viewModel: LeagueFilterViewModel): ViewModel

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributesTableFragment(): TableFragment

    @Binds
    @IntoMap
    @ViewModelKey(TableViewModel::class)
    abstract fun bindTableViewModel(viewModel: TableViewModel): ViewModel
}
