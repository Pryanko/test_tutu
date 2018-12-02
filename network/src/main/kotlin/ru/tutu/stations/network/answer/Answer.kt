package ru.tutu.stations.network.answer

import com.google.gson.annotations.SerializedName
import ru.tutu.stations.network.model.Dissect

/**
 * @author Grigoriy Pryamov.
 */
class Answer<E>(@SerializedName("citiesFrom") override val values: List<E>) : Dissect<E> {

    override fun get(): List<E> = values
}