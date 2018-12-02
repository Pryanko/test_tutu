package ru.tutu.stations

import dagger.Component
import ru.tutu.stations.localdb.di.LocalDataBaseModule
import ru.tutu.stations.network.ApiModule
import ru.tutu.stations.ui.activity.launcher.LauncherScreenComponent
import ru.tutu.stations.ui.mvp.core.MvpProcessor
import javax.inject.Singleton

/**
 * DI - приложения
 *
 * @author Grigoriy Pryamov.
 */
@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        LocalDataBaseModule::class]
)
interface AppComponent {

    fun inject(appInitProvider: AppInitProvider)

    fun mvpProcessor(): MvpProcessor

    fun launcherScreenComponent(): LauncherScreenComponent
}