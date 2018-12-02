package ru.tutu.stations.ui.mvp

import ru.tutu.stations.ui.mvp.presenter.MvpPresenter

/**
 * Фабрика презентеров
 *
 * @author Grigoriy Pryamov.
 */
interface PresenterProvider<P : MvpPresenter<MvpView>> {
    fun providePresenter(): P
}