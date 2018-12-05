package ru.tutu.stations.ui.activity.main

import dagger.Subcomponent
import ru.tutu.stations.di.ScreenScope

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
@Subcomponent
interface MainScreenComponent {
    fun mainComponentBuilder(): MainComponent.Builder
}