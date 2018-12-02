package ru.tutu.stations.data.base

import io.reactivex.Completable
import io.reactivex.Single
import ru.tutu.stations.localdb.base.DbTransaction
import ru.tutu.stations.network.model.Dissect
import ru.tutu.stations.network.model.Response

/**
 * @author Grigoriy Pryamov.
 */
abstract class BaseSynchronizer<Entity, Answer : Dissect<Entity>>(
    private val transaction: DbTransaction
) {

    /**
     * Основной метод синхронизации
     */
    fun sync(): Completable {
        return apiSource()
            .doOnSuccess { response ->
                transaction.callInTx {
                    store(response.data.get())
                }
            }
            .toCompletable()
    }

    protected abstract fun apiSource(): Single<Response<Answer>>

    /**
     * Метод записывающий данные в бд - реализаци вынесена в наследника, для реализации разной логики
     *
     * @param entities список для записи
     */
    protected abstract fun store(entities: List<Entity>)
}