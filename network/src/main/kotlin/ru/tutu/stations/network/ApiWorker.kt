package ru.tutu.stations.network

import io.reactivex.Single
import ru.tutu.stations.network.answer.City

/**
 * @author Grigoriy Pryamov.
 */
interface ApiWorker {
    /**
     * Список стран с данными о станциях
     */
    fun allStations(): Single<List<City>>
}