package ru.tutu.stations.data.base

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import ru.tutu.stations.data.*
import ru.tutu.stations.localdb.base.DbTransaction
import ru.tutu.stations.network.model.Dissect
import ru.tutu.stations.network.model.Response

/**
 * @author Grigoriy Pryamov.
 */
abstract class BaseSynchronizer<Entity, Answer : Dissect<Entity>>(
    private val transaction: DbTransaction
) {

    protected val statusSubject = BehaviorSubject.create<SyncStatus>()!!

    /**
     * Основной метод синхронизации
     */
    protected fun sync(): Completable {
        return apiSource()
            .doOnSubscribe { statusSubject.onNext(DownloadStatus) }
            .observeOn(Schedulers.io())
            .flatMapCompletable { response ->
                statusSubject.onNext(ProcessStatus)
                transaction.callInTx {
                    store(response.data.get())
                }
                return@flatMapCompletable Completable.complete()
            }
            .onErrorResumeNext { error ->
                if (syncEnable()) statusSubject.onNext(DeferredStatus) else statusSubject.onNext(ErrorStatus)
                return@onErrorResumeNext Completable.error(error)
            }
    }

    protected abstract fun apiSource(): Single<Response<Answer>>

    /**
     * Метод записывающий данные в бд - реализаци вынесена в наследника, для реализации разной логики
     *
     * @param entities список для записи
     */
    protected abstract fun store(entities: List<Entity>)

    /**
     * Разрешение на продолжение работы (имеются ли данные для показа)
     */
    protected abstract fun syncEnable(): Boolean
}