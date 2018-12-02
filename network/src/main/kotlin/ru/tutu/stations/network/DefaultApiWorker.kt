package ru.tutu.stations.network

import io.reactivex.Single
import ru.tutu.stations.network.answer.Answer
import ru.tutu.stations.network.answer.City
import ru.tutu.stations.network.model.Response

/**
 * @author Grigoriy Pryamov.
 */
class DefaultApiWorker(private val api: Api, private val responseConverter: ResponseConverter) : ApiWorker {

    override fun allStations(): Single<Response<Answer<City>>> {
        return api.allStations()
            .map { responseConverter.convert(it) }
    }
}