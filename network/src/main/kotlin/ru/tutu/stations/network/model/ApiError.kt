package ru.tutu.stations.network.model

/**
 * Ошибка API.
 *
 * @author Grigoriy Pryamov.
 */
data class ApiError(val code: Int, val message: String)