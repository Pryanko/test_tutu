package ru.tutu.stations.ui.activity.main

import ru.tutu.stations.ui.activity.main.adapter.model.FatStation
import ru.tutu.stations.ui.mvp.view.MvpView

/**
 * @author Grigoriy Pryamov.
 */
interface MainView : MvpView {
    fun showFatStation(fatStation: FatStation)
}