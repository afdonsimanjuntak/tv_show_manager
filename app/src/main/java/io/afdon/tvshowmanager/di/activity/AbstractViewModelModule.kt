package io.afdon.tvshowmanager.di.activity

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.afdon.add.ui.AddTvShowViewModel
import io.afdon.core.viewmodel.AssistedViewModelFactory
import io.afdon.home.ui.HomeViewModel
import io.afdon.list.ui.TvShowsViewModel

@Module
interface AbstractViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModelFactory(
        factory: HomeViewModel.Factory
    ): AssistedViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(AddTvShowViewModel::class)
    fun bindAddViewModelFactory(
        factory: AddTvShowViewModel.Factory
    ): AssistedViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(TvShowsViewModel::class)
    fun bindListViewModelFactory(
        factory: TvShowsViewModel.Factory
    ): AssistedViewModelFactory<out ViewModel>
}