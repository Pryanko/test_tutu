package ru.tutu.stations.ui.activity.launcher

import ru.tutu.stations.data.SyncStatus
import ru.tutu.stations.ui.mvp.view.MvpView

/**
 * @author Grigoriy Pryamov.
 */
interface LauncherView : MvpView {
    fun setSyncStatus(syncStatus: SyncStatus)
}