package ru.tutu.stations.ui.mvp.core

import ru.tutu.stations.ui.mvp.MvpView
import ru.tutu.stations.ui.mvp.presenter.MvpPresenter
import java.util.*
import javax.inject.Inject

/**
 * @author Grigoriy Pryamov.
 */
class PresenterStore @Inject constructor() {

    private val presenters = HashMap<String, MvpPresenter<MvpView>>()

    fun getPresenter(tag: String): MvpPresenter<MvpView>? {
        return presenters[tag]
    }

    fun putPresenter(tag: String, presenter: MvpPresenter<MvpView>) {
        presenters[tag] = presenter
    }

    fun removePresenter(tag: String) {
        presenters.remove(tag)
    }
}