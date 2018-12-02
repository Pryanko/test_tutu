package ru.tutu.stations.ui.activity.launcher

import dagger.Subcomponent
import ru.tutu.stations.di.ActivityScope
import ru.tutu.stations.ui.activity.base.ActivityModule

/**
 * @author Grigoriy Pryamov.
 */
@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface LauncherComponent {

    fun inject(launcherActivity: LauncherActivity)

    fun launcherPresenter(): LauncherPresenter

    @Subcomponent.Builder
    interface Builder {
        fun activityModule(activityModule: ActivityModule): LauncherComponent.Builder

        fun build(): LauncherComponent
    }
}