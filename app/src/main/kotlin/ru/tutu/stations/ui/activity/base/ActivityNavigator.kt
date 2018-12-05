package ru.tutu.stations.ui.activity.base

import android.app.Activity
import android.content.Intent
import ru.tutu.stations.R
import ru.tutu.stations.ui.activity.main.MainActivity

import javax.inject.Inject

/**
 * Основной класс для навигации в приложении
 *
 * @author Grigoriy Pryamov.
 */
class ActivityNavigator @Inject constructor() {

    fun navigateToMainActivity(activity: Activity) {
        val intent = MainActivity.getCallingIntent(activity)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        //intent.flags =  or Intent.FLAG_ACTIVITY_SINGLE_TOP
        activity.startActivity(intent)
        animForward(activity)
    }

    fun navigateBack(activity: Activity) {
        activity.finish()
        animBack(activity)
    }

    private fun animForward(activity: Activity) {
        activity.overridePendingTransition(R.anim.slide_from_right, R.anim.zero_animation)
    }

    private fun animBack(activity: Activity) {
        activity.overridePendingTransition(R.anim.zero_animation, R.anim.slide_to_right)
    }
}
