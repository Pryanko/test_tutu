package ru.tutu.stations.ui.mvp.core

import java.util.*
import javax.inject.Inject

/**
 * @author Grigoriy Pryamov.
 */
class PresenterCounter @Inject constructor() {

    private val connections = HashMap<String, Int>()

    fun incrementCounter(tag: String) {
        var currentCount: Int? = connections[tag]
        if (currentCount == null) {
            currentCount = 1
            connections[tag] = currentCount
            return
        }
        connections[tag] = currentCount + 1
    }

    fun decrementCounter(tag: String): Boolean {
        val currentCount = connections[tag] ?: throw IllegalStateException("Invalid connections count")
        if (currentCount == 1) {
            connections.remove(tag)
            return true
        }
        connections[tag] = currentCount - 1
        return false
    }
}