package ru.tutu.stations.network

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import ru.tutu.stations.network.answer.Answer
import ru.tutu.stations.network.answer.City

/**
 * Api
 *
 * @author Grigoriy Pryamov.
 */
interface Api {
    /**
     * Список стран с данными о станциях
     */
    @GET("allStations.json")
    fun allStations(): Single<Response<Answer<City>>>
}