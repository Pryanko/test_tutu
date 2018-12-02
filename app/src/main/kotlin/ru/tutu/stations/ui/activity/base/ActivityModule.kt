package ru.tutu.stations.ui.activity.base

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides

/**
 * @author Grigoriy Pryamov.
 */
@Module
class ActivityModule(private val baseActivity: BaseActivity) {

    @Provides
    fun activity(): Activity {
        return baseActivity
    }

    @Provides
    fun appCompatActivity(): AppCompatActivity {
        return baseActivity
    }

    @Provides
    fun baseActivity(): BaseActivity {
        return baseActivity
    }
}