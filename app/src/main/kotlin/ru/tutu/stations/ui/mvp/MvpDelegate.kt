package ru.tutu.stations.ui.mvp

import android.os.Bundle
import ru.tutu.stations.ui.mvp.core.MvpProcessor
import ru.tutu.stations.ui.mvp.presenter.MvpPresenter
import java.util.*

/**
 * @author Grigoriy Pryamov.
 */
class MvpDelegate(private val mvpProcessor: MvpProcessor, private val view: MvpView) {

    private val childDelegates = HashMap<String?, MvpDelegate>()
    private val presenters = HashMap<MvpPresenter<MvpView>, String>()
    private var keepAliveProvider: KeepAliveProvider? = null
    private var delegateTag: String? = null

    fun <P : MvpPresenter<MvpView>> getPresenter(
        presenterProvider: PresenterProvider<P>,
        tag: String
    ): MvpPresenter<MvpView>? {
        val presenter = mvpProcessor.getPresenter(presenterProvider, tag)
        if (presenter != null) presenters[presenter] = tag
        return presenter
    }

    fun <P : MvpPresenter<MvpView>> getPresenter(
        presenterProvider: PresenterProvider<P>,
        pClass: Class<P>
    ): MvpPresenter<MvpView>? {
        val tag = delegateTag + "_" + pClass.name
        val presenter = mvpProcessor.getPresenter(presenterProvider, tag)
        if (presenter != null) presenters[presenter] = tag
        return presenter
    }

    fun setKeepAliveProvider(keepAliveProvider: KeepAliveProvider) {
        this.keepAliveProvider = keepAliveProvider
    }

    fun init(bundle: Bundle?) {
        if (bundle != null) {
            delegateTag = bundle.getString(MVP_DELEGATE_TAG)
        }
        if (delegateTag == null) {
            delegateTag = generateTag()
        }
    }

    fun init(parent: MvpDelegate, id: String) {
        delegateTag = parent.delegateTag + "$" + id
        parent.childDelegates[delegateTag] = this
    }

    fun bindView() {
        for (mvpPresenter in presenters.keys) {
            mvpPresenter.bind(view)
        }
        for (childDelegate in childDelegates.values) {
            childDelegate.bindView()
        }
    }

    fun unbindView() {
        for (childDelegate in childDelegates.values) {
            childDelegate.unbindView()
        }
        for (mvpPresenter in presenters.keys) {
            mvpPresenter.unbind(view)
        }
    }

    fun saveState(outState: Bundle) {
        outState.putString(MVP_DELEGATE_TAG, delegateTag)
    }

    fun destroy(keepAlive: Boolean) {
        for (childDelegate in childDelegates.values) {
            if (keepAliveProvider == null) {
                childDelegate.destroy(keepAlive)
            } else {
                childDelegate.destroy(keepAliveProvider!!.keepAlive(keepAlive))
            }
        }
        for ((key, value) in presenters) {
            mvpProcessor.freePresenter(key, value, keepAlive)
        }
    }

    private fun generateTag(): String {
        return view.toString()
    }

    companion object {
        const val MVP_DELEGATE_TAG = "MVP_DELEGATE_TAG"
    }
}