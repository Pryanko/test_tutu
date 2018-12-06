package ru.tutu.stations.ui.activity.main

import io.reactivex.disposables.Disposables
import ru.digipeople.logger.LoggerFactory
import ru.tutu.stations.ui.activity.launcher.LauncherPresenter
import ru.tutu.stations.ui.activity.main.adapter.MainAdapter
import ru.tutu.stations.ui.activity.main.interactor.CountryStationLoader
import ru.tutu.stations.ui.mvp.presenter.BaseMvpViewStatePresenter
import ru.tutu.stations.util.AppSchedulers
import javax.inject.Inject

/**
 * @author Grigoriy Pryamov.
 */
class MainPresenter @Inject constructor(
    mainViewState: MainViewState,
    private val countryStationLoader: CountryStationLoader,
    private val mainAdapter: MainAdapter
) :
    BaseMvpViewStatePresenter<MainView, MainViewState>(mainViewState) {

    private val logger = LoggerFactory.getLogger(LauncherPresenter::class)

    private var loadDisposable = Disposables.disposed()
    private var singleLoadDisposable = Disposables.disposed()

    override fun onInitialize() {
        logger.trace("onInitialize")
        loadDisposable = countryStationLoader.load()
            .subscribeOn(AppSchedulers.db())
            .observeOn(AppSchedulers.ui())
            .subscribe { countries -> mainAdapter.setCountries(countries) }
    }

    fun onStationClicked(id: Long) {
        singleLoadDisposable = countryStationLoader.stationLoad(id)
            .subscribeOn(AppSchedulers.db())
            .observeOn(AppSchedulers.ui())
            .subscribe { fatStation -> view.showFatStation(fatStation) }
    }

    override fun destroy() {
        loadDisposable.dispose()
        singleLoadDisposable.dispose()
        super.destroy()
    }
}