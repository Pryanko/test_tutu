package ru.tutu.stations.ui.mvp.presenter

import ru.tutu.stations.ui.mvp.MvpView

/**
 * Скелетная реализация {@link MvpPresenter}.
 *
 * @author Grigoriy Pryamov.
 */
abstract class BaseMvpPresenter<V : MvpView> : MvpPresenter<V> {
    /**
     * Представление, ассоциированное с презентером.
     */
    protected var view: V? = null
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
