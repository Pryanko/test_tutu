package ru.tutu.stations.ui.mvp.presenter

import ru.tutu.stations.ui.mvp.MvpView

/**
 * MVP презентер.
 *
 * @param <V> {@link MvpView}, c которой работает презентер.
 * @author Grigoriy Pryamov.
 */
interface MvpPresenter<V : MvpView> {
    /**
     * Привязывает [MvpView] к презентеру
     *
     * @param view Представление
     */
    fun bind(view: V)

    /**
     * Отвязывает [MvpView] от презентера
     *
     * @param view
     */
    fun unbind(view: V)

    /**
     * Вызывается при очищении ссылки на презентер.
     * Последняя точка, в которой нужно отвязаться от всех static ссылок.
     */
    fun destroy()
}