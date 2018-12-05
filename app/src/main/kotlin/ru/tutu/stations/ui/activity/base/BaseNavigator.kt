package ru.tutu.stations.ui.activity.base

import ru.tutu.stations.ui.mvp.view.MvpView

/**
 * Базовый персональный навигатор
 *
 * @author Grigoriy Pryamov.
 */
abstract class BaseNavigator<A : MvpView> protected constructor(protected val activityNavigator: ActivityNavigator) {

    protected var baseActivity: A? = null

    //Методы onPause и onResume - делегируем наследнику - возможно дополнительное поведение
    abstract fun onResume(activity: A)

    abstract fun onPause()

    /**
     * Выполняет операцию c проверкой активити на null
     *
     * @param action Операция для выполнения
     */
    protected fun forSafeAction(action: () -> Unit) {
        if (baseActivity != null) {
            action.invoke()
        }
    }
}
