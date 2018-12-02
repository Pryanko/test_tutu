package ru.tutu.stations.ui.mvp.viewstate

import ru.tutu.stations.ui.mvp.MvpView
import java.util.*

/**
 * Скелетная реализация {@link MvpViewState}.
 *
 * @author Grigoriy Pryamov.
 */
abstract class BaseMvpViewState<V : MvpView> : MvpViewState<V> {

    private val views = ArrayList<V>()

    override fun attachView(view: V) {
        views.add(view)
        onViewAttached(view)
    }

    override fun detachView(view: V) {
        views.remove(view)
        onViewDetached(view)
    }

    /**
     * Колбэк при присоединении view.
     *
     * @param view Присоединенная view
     */
    protected abstract fun onViewAttached(view: V)

    /**
     * Колбэк при отсоединении view.
     *
     * @param view Отсоединенная view
     */
    protected abstract fun onViewDetached(view: V)

    /**
     * Выполняет операцию на всех привязанных view.
     *
     * @param action Операция для выполнения
     */
    protected fun forEachView(action: Consumer<V>) {
        for (view in views) {
            action.accept(view)
        }
    }

    /**
     * Операция, выполняемая на view.
     *
     * @param <T> Тип view [MvpView]
    </T> */
    interface Consumer<T> {

        /**
         * Выполняет операцию на view.
         *
         * @param view [MvpView]
         */
        fun accept(view: T)
    }
}