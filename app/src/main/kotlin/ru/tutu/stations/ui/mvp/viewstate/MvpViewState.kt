package ru.tutu.stations.ui.mvp.viewstate

import ru.tutu.stations.ui.mvp.MvpView

/**
 * Состояние {@link MvpView}.
 *
 * @param <V> {@link MvpView}
 * @author Grigoriy Pryamov.
 */
interface MvpViewState<V : MvpView> {
    /**
     * Присоединение view.
     *
     * @param view Присоединенная view
     */
    fun attachView(view: V)

    /**
     * Отсоединение view.
     *
     * @param view Отсоединенная view
     */
    fun detachView(view: V)
}