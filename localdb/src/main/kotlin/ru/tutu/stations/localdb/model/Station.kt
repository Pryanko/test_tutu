package ru.tutu.stations.localdb.model

import ru.tutu.stations.localdb.model.base.ModelWithId

/**
 * @author Grigoriy Pryamov.
 */
class Station : ModelWithId<Long> {
    /**
     * Id
     */
    override var id = 0L
    /**
     * Id региона
     */
    var countryId = 0L
    /**
     * Местоположение
     */
    var point = Point()
    /**
     * Описание наименования
     */
    var districtTitle = ""
    /**
     * Наименование региона
     */
    var regionTitle = ""
    /**
     * Id станции
     */
    var stationId = 0L
    /**
     * Наименование станции
     */
    var stationTitle = ""
}