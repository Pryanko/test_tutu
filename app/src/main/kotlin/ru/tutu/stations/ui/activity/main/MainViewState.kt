package ru.tutu.stations.ui.activity.main

import ru.tutu.stations.ui.activity.main.adapter.model.FatStation
import ru.tutu.stations.ui.mvp.viewState.BaseMvpViewState
import javax.inject.Inject

/**
 * @author Grigoriy Pryamov.
 */
class MainViewState @Inject constructor() : BaseMvpViewState<MainView>(), MainView {


    override fun onViewAttached(view: MainView?) {

    }

    override fun onViewDetached(view: MainView?) {

    }

    override fun showFatStation(fatStation: FatStation) {
        forEachView { view -> view.showFatStation(fatStation) }
    }
}