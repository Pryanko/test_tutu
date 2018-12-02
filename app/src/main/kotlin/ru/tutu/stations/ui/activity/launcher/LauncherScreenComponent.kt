package ru.tutu.stations.ui.activity.launcher

import dagger.Subcomponent
import ru.tutu.stations.di.ScreenScope

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
@Subcomponent
interface LauncherScreenComponent {
    fun launcherComponentBuilder(): LauncherComponent.Builder
}