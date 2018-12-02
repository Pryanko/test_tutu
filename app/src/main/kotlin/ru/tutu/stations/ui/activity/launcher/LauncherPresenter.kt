package ru.tutu.stations.ui.activity.launcher

import ru.digipeople.logger.LoggerFactory
import ru.tutu.stations.ui.mvp.presenter.BaseMvpViewStatePresenter
import javax.inject.Inject

/**
 * @author Grigoriy Pryamov.
 */
class LauncherPresenter @Inject constructor(launcherViewState: LauncherViewState) :
    BaseMvpViewStatePresenter<LauncherView, LauncherViewState>(launcherViewState) {

    private val logger = LoggerFactory.getLogger(LauncherPresenter::class)

    override fun onInitialize() {
        logger.trace("onInitialize")
    }
}