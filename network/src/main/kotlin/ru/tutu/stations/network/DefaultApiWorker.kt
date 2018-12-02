package ru.tutu.stations.network

import io.reactivex.Single
import ru.tutu.stations.network.answer.City

/**
 * @author Grigoriy Pryamov.
 */
class DefaultApiWorker(val api: Api, private val responseConverter: ResponseConverter) : ApiWorker {

    override fun allStations(): Single<List<City>> {
        return api.allStations()
            .map { responseConverter.convertToEntity(it).get() }
    }
}