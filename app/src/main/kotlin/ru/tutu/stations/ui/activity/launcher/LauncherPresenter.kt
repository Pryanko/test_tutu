package ru.tutu.stations.ui.activity.launcher

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import ru.digipeople.logger.LoggerFactory
import ru.tutu.stations.data.DataSynchronizer
import ru.tutu.stations.ui.mvp.presenter.BaseMvpViewStatePresenter
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

    override fun onInitialize() {
        logger.trace("onInitialize")
        syncDisposable = dataSynchronizer.sync()
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { t -> logger.trace("dataSynchronizer: ON") }
            .subscribe {
                logger.trace("dataSynchronizer: OFF")
            }
    }

    override fun destroy() {
        syncDisposable.dispose()
        super.destroy()
    }
}