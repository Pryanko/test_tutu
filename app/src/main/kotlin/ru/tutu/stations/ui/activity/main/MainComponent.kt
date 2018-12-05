package ru.tutu.stations.ui.activity.main

import dagger.Subcomponent
import ru.tutu.stations.di.ActivityScope
import ru.tutu.stations.ui.activity.base.ActivityModule

/**
 * @author Grigoriy Pryamov.
 */
@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface MainComponent {

    fun inject(mainActivity: MainActivity)

    fun mainPresenter(): MainPresenter

    @Subcomponent.Builder
    interface Builder {
        fun activityModule(activityModule: ActivityModule): MainComponent.Builder

        fun build(): MainComponent
    }
}