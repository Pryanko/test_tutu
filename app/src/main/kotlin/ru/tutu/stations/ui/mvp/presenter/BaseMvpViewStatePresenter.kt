package ru.tutu.stations.ui.mvp.presenter

import ru.tutu.stations.ui.mvp.MvpView
import ru.tutu.stations.ui.mvp.viewstate.MvpViewState

/**
 * Скелетная реализация {@link MvpPresenter, работающего с прослойкой {@link MvpViewState }.
 *
 * @author Grigoriy Pryamov.
 */
abstract class BaseMvpViewStatePresenter<V : MvpView, VS : MvpViewState<V>> : MvpPresenter<V> {
    /**
     * Представление, ассоциированное с презентером.
     */
    protected var view: V? = null
    /**
     * [MvpViewState] прослойка
     */
    private lateinit var viewState: VS
    /**
     * Флаг, что презентер проинициализирован, т.е. начал свою работу
     */
    private var initialized = false

    fun initialize() {
        if (!initialized) {
            initialized = true
            onInitialize()
        }
    }

    protected abstract fun onInitialize()

    override fun bind(view: V) {
        this.view = view
    }

    override fun unbind(view: V) {
        this.view = null
    }

    override fun destroy() {}
}