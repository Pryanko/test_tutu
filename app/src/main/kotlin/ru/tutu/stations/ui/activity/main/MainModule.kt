package ru.tutu.stations.ui.activity.main

import dagger.Binds
import dagger.Module
import ru.tutu.stations.di.ScreenScope
import ru.tutu.stations.ui.activity.main.adapter.MainAdapter
import ru.tutu.stations.ui.activity.main.adapter.MainAdapterImpl

/**
 * @author Grigoriy Pryamov.
 */
@Module
abstract class MainModule {

    @Binds
    abstract fun mainAdapter(mainAdapter: MainAdapterImpl): MainAdapter
}