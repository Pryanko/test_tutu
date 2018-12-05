package ru.tutu.stations.ui.activity.launcher

import io.reactivex.disposables.Disposables
import ru.digipeople.logger.LoggerFactory
import ru.tutu.stations.data.DataSynchronizer
import ru.tutu.stations.ui.mvp.presenter.BaseMvpViewStatePresenter
import ru.tutu.stations.util.AppSchedulers
import javax.inject.Inject

/**
 * @author Grigoriy Pryamov.
 */
class LauncherPresenter @Inject constructor(
    launcherViewState: LauncherViewState,
    private val dataSynchronizer: DataSynchronizer
) :
    BaseMvpViewStatePresenter<LauncherView, LauncherViewState>(launcherViewState) {

    private val logger = LoggerFactory.getLogger(LauncherPresenter::class)

    private var syncDisposable = Disposables.disposed()
    private var statusDisposable = Disposables.disposed()

    override fun onInitialize() {
        logger.trace("onInitialize")
        statusDisposable = dataSynchronizer.syncStatusChanges()
            .subscribeOn(AppSchedulers.background())
            .observeOn(AppSchedulers.ui())
            .subscribe { syncStatus -> view.setSyncStatus(syncStatus) }
        requestUpdate()
    }

    private fun requestUpdate() {
        syncDisposable = dataSynchronizer.dataSync()
            .subscribeOn(AppSchedulers.network())
            .subscribeOn(AppSchedulers.ui())
            .doOnSubscribe { _ -> logger.trace("dataSynchronizer: ON") }
            .subscribe({
                logger.trace("dataSynchronizer: OFF")
            }, { error -> logger.error(error) })
    }

    override fun destroy() {
        syncDisposable.dispose()
        statusDisposable.dispose()
        super.destroy()
    }

    fun onClickedBtnNext() {
        logger.trace("onClickedBtnNext")
    }

    fun onClickedBtnRefresh() {
        logger.trace("onClickedBtnRefresh")
        requestUpdate()
    }
}