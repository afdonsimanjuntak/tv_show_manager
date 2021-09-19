package io.afdon.tvshowmanager.di.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentFactory
import dagger.BindsInstance
import dagger.Subcomponent
import io.afdon.add.di.AddTvShowModule
import io.afdon.list.di.TvShowsModule

@ActivityScope
@Subcomponent(modules = [
    FragmentModule::class,
    ViewModelModule::class,
    AbstractViewModelModule::class,
    NavigationModule::class,
    AddTvShowModule::class,
    TvShowsModule::class
])
interface ActivitySubcomponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(@BindsInstance appCompatActivity: AppCompatActivity) : ActivitySubcomponent
    }

    fun getFragmentFactory() : FragmentFactory
}