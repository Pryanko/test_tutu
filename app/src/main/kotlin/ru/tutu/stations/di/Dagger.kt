package ru.tutu.stations.di

import ru.tutu.stations.AppComponent

/**
 * @author Grigoriy Pryamov.
 */
object Dagger {

    private lateinit var appComponent: AppComponent

    fun get(): AppComponent = appComponent

    fun set(appComponent: AppComponent) {
        Dagger.appComponent = appComponent
    }
}