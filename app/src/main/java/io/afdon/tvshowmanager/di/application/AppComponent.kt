package io.afdon.tvshowmanager.di.application

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import io.afdon.tvshowmanager.di.activity.ActivitySubcomponent
import javax.inject.Singleton

@Singleton
@Component(modules = [
    SubcomponentModule::class,
    AppModule::class,
    ApolloModule::class
])
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context) : AppComponent
    }

    fun getActivitySubcomponentFactory() : ActivitySubcomponent.Factory
}