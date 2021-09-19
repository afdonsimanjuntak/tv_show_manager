package io.afdon.tvshowmanager.di.application

import dagger.Module
import io.afdon.tvshowmanager.di.activity.ActivitySubcomponent

@Module(subcomponents = [
    ActivitySubcomponent::class
])
object SubcomponentModule