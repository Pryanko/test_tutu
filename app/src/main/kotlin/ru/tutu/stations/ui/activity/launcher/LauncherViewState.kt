package ru.tutu.stations.ui.activity.launcher

import ru.tutu.stations.data.SyncStatus
import ru.tutu.stations.data.UnknownStatus
import ru.tutu.stations.ui.mvp.viewState.BaseMvpViewState
import javax.inject.Inject

/**
 * @author Grigoriy Pryamov.
 */
class LauncherViewState @Inject constructor() : BaseMvpViewState<LauncherView>(), LauncherView {

    private var syncStatus: SyncStatus = UnknownStatus

    override fun onViewAttached(view: LauncherView) {
        view.setSyncStatus(syncStatus)
    }

    override fun onViewDetached(view: LauncherView) {}

    override fun setSyncStatus(syncStatus: SyncStatus) {
        this.syncStatus = syncStatus
        forEachView { view -> view.setSyncStatus(syncStatus) }
    }
}