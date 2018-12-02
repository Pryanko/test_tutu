package ru.tutu.stations.localdb.room.entity.base

/**
 * Модуль локальной БД
 *
 * @author Grigoriy Pryamov.
 */
interface EntityWithId<Id> {
    /**
     * Id записи
     */
    var id: Id
}