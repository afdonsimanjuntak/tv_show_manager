package io.afdon.tvshowmanager.di.activity

import dagger.Binds
import dagger.Module
import io.afdon.home.nav.HomeNavigation
import io.afdon.tvshowmanager.navigation.HomeNavigationImpl

@Module
interface NavigationModule {

    @Binds
    fun bindHomeNavigation(nav: HomeNavigationImpl) : HomeNavigation
}