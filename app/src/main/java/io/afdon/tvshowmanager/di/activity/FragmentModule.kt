package io.afdon.tvshowmanager.di.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.afdon.add.ui.AddTvShowFragment
import io.afdon.home.ui.HomeFragment
import io.afdon.list.ui.TvShowsFragment

@Module
interface FragmentModule {

    @Binds
    @ActivityScope
    fun bindFragmentFactory(appFragmentFactory: AppFragmentFactory) : FragmentFactory

    @Binds
    @IntoMap
    @FragmentKey(HomeFragment::class)
    fun bindHomeFragment(fragment: HomeFragment) : Fragment

    @Binds
    @IntoMap
    @FragmentKey(AddTvShowFragment::class)
    fun bindAddFragment(tvShowFragment: AddTvShowFragment) : Fragment

    @Binds
    @IntoMap
    @FragmentKey(TvShowsFragment::class)
    fun bindListFragment(fragment: TvShowsFragment) : Fragment
}