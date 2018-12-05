package ru.tutu.stations.ui.activity.main

import ru.digipeople.logger.LoggerFactory
import ru.tutu.stations.ui.activity.launcher.LauncherPresenter
import ru.tutu.stations.ui.mvp.presenter.BaseMvpViewStatePresenter
import javax.inject.Inject

/**
 * @author Grigoriy Pryamov.
 */
class MainPresenter @Inject constructor(mainViewState: MainViewState) :
    BaseMvpViewStatePresenter<MainView, MainViewState>(mainViewState) {

    private val logger = LoggerFactory.getLogger(LauncherPresenter::class)

    override fun onInitialize() {
        logger.trace("onInitialize")
    }
}