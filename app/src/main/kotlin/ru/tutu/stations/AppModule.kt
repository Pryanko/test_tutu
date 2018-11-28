package ru.tutu.stations

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * @author Grigoriy Pryamov.
 */
@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

    @Provides
    fun context(): Context {
        return app
    }
}