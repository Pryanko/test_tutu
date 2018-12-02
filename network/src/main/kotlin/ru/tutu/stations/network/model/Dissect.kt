package ru.tutu.stations.network.model

/**
 * Маркерный интерфейс для Answer
 *
 * @author Grigoriy Pryamov.
 */
interface Dissect<M> {

    val values: List<M>?
    /**
     * Вспомогательный метод
     */
    fun get(): List<M>
}