package ru.tutu.stations.localdb.base

import java.util.concurrent.Callable

/**
 * @author Grigoriy Pryamov.
 */
interface DbTransaction {

    /**
     * Начинает транзакцию
     */
    fun beginTransaction()

    /**
     * Завершает транзакцию
     */
    fun endTransaction()

    /**
     * Помечает успешную транзакцию
     */
    fun setTransactionSuccessful()

    /**
     * Выполняет блок кода в транзакции
     */
    fun <T> callInTx(callable: Callable<T>): T

    /**
     * Выполняет блок кода в транзакции
     */
    fun callInTx(runnable: Runnable)
}