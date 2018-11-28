package ru.tutu.stations

import dagger.Component
import javax.inject.Singleton

/**
 * DI - приложения
 *
 * @author Grigoriy Pryamov.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(appInitProvider: AppInitProvider)

}