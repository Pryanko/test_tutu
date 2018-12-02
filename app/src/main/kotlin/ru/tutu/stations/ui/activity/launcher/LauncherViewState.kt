package ru.tutu.stations.ui.activity.launcher

import ru.tutu.stations.ui.mvp.viewState.BaseMvpViewState
import javax.inject.Inject

/**
 * @author Grigoriy Pryamov.
 */
class LauncherViewState @Inject constructor() : BaseMvpViewState<LauncherView>(), LauncherView {

    override fun onViewAttached(view: LauncherView) {

    }

    override fun onViewDetached(view: LauncherView) {

    }
}