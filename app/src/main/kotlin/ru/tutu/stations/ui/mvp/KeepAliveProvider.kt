package ru.tutu.stations.ui.mvp

/**
 * @author Grigoriy Pryamov.
 */
interface KeepAliveProvider {
    fun keepAlive(parentKeepAlive: Boolean): Boolean
}