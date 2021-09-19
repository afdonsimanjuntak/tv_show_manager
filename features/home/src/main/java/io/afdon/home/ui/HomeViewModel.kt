package io.afdon.home.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.afdon.core.viewmodel.AssistedViewModelFactory
import io.afdon.home.nav.HomeNavigation

class HomeViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val navigation: HomeNavigation
) : ViewModel() {

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<HomeViewModel>

    fun addTvSHow() = navigation.openAddTvShowAddForm()

    fun showSavedTvShows() = navigation.openTvShowList()
}