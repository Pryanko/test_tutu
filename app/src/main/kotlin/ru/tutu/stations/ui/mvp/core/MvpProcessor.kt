package ru.tutu.stations.ui.mvp.core

import ru.tutu.stations.ui.mvp.MvpView
import ru.tutu.stations.ui.mvp.PresenterProvider
import ru.tutu.stations.ui.mvp.presenter.MvpPresenter
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Grigoriy Pryamov.
 */
@Singleton
class MvpProcessor @Inject constructor(
    private val presenterStore: PresenterStore,
    private val presenterCounter: PresenterCounter
) {

    fun <P : MvpPresenter<MvpView>> getPresenter(presenterProvider: PresenterProvider<P>, tag: String)
            : MvpPresenter<MvpView>? {
        var presenter = presenterStore.getPresenter(tag)
        if (presenter == null) {
            presenter = presenterProvider.providePresenter()
            presenterStore.putPresenter(tag, presenter)
        }
        presenterCounter.incrementCounter(tag)
        return presenter
    }

    fun <P : MvpPresenter<MvpView>> freePresenter(presenter: P, tag: String, keepAlive: Boolean) {
        if (presenterCounter.decrementCounter(tag)) {
            if (!keepAlive) {
                presenterStore.removePresenter(tag)
                presenter.destroy()
            }
        }
    }

}