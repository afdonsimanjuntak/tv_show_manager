package io.afdon.tvshowmanager

import android.app.Application
import io.afdon.tvshowmanager.di.application.AppComponent
import io.afdon.tvshowmanager.di.application.DaggerAppComponent

class TvShowManagerApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}