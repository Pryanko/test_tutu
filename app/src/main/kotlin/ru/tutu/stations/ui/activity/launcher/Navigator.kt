package ru.tutu.stations.ui.activity.launcher

import ru.tutu.stations.di.ScreenScope
import ru.tutu.stations.ui.activity.base.ActivityNavigator
import ru.tutu.stations.ui.activity.base.BaseNavigator
import javax.inject.Inject

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
class Navigator @Inject constructor(activityNavigator: ActivityNavigator) :
    BaseNavigator<LauncherActivity>(activityNavigator) {

    fun navigateToMainActivity() {
        forSafeAction { activityNavigator.navigateToMainActivity(baseActivity as LauncherActivity) }
    }

    override fun onPause() {
        baseActivity = null
    }

    override fun onResume(activity: LauncherActivity) {
        baseActivity = activity
    }
}