package com.belzsoftware.futspect.ui.tables

import androidx.lifecycle.ViewModel
import com.belzsoftware.futspect.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class TableModule {

    @ContributesAndroidInjector
    abstract fun contributesTableFragment() : TableFragment

    @Binds
    @IntoMap
    @ViewModelKey(TableViewModel::class)
    abstract fun bindAgendaViewModel(viewModel: TableViewModel) : ViewModel
}