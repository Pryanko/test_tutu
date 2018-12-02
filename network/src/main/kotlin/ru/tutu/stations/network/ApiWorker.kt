package ru.tutu.stations.network

import io.reactivex.Single
import ru.tutu.stations.network.answer.Answer
import ru.tutu.stations.network.answer.City
import ru.tutu.stations.network.model.Response

/**
 * @author Grigoriy Pryamov.
 */
interface ApiWorker {
    /**
     * Список стран с данными о станциях
     */
    fun allStations(): Single<Response<Answer<City>>>
}