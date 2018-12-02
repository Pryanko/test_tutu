package ru.tutu.stations.localdb.model.base

/**
 * @author Grigoriy Pryamov.
 */
interface ModelWithId<Id> {
    /**
     * Id
     */
    var id: Id
}