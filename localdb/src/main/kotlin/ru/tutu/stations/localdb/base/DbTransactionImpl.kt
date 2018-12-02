package ru.tutu.stations.localdb.base

import ru.tutu.stations.localdb.AppDatabase
import java.util.concurrent.Callable

/**
 * @author Grigoriy Pryamov.
 */
class DbTransactionImpl constructor(private var appDatabase: AppDatabase) : DbTransaction {

    override fun beginTransaction() {
        appDatabase.beginTransaction()
    }

    override fun endTransaction() {
        appDatabase.endTransaction()
    }

    override fun setTransactionSuccessful() {
        appDatabase.setTransactionSuccessful()
    }

    override fun <T> callInTx(callable: Callable<T>): T {
        return appDatabase.runInTransaction(callable)
    }

    override fun callInTx(runnable: Runnable) {
        appDatabase.runInTransaction(runnable)
    }

}