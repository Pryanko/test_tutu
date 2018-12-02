package ru.tutu.stations.network.answer

import com.google.gson.annotations.SerializedName
import ru.tutu.stations.network.model.Dissect

/**
 * @author Grigoriy Pryamov.
 */
class Answer(@SerializedName("citiesFrom") override val values: List<City>) : Dissect<City> {

    override fun get(): List<City> = values
}