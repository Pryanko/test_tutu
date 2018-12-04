package ru.tutu.stations.localdb.model

import ru.tutu.stations.localdb.model.base.ModelWithId

/**
 * @author Grigoriy Pryamov.
 */
class Country : ModelWithId<Long> {
    /**
     * Id
     */
    override var id = 0L
    /**
     * Наименование региона - страны
     */
    var countryTitle = ""
    /**
     * Наименование региона - страны (в нижнем реестре)
     */
    var countryTitleToLower = ""
    /**
     * Описание
     */
    var districtTitle = ""
}