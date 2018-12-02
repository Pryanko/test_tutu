package ru.tutu.stations

import dagger.Component
import ru.tutu.stations.network.ApiModule
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
        ApiModule::class]
)
interface AppComponent {

    fun inject(appInitProvider: AppInitProvider)

    fun mvpProcessor(): MvpProcessor

}