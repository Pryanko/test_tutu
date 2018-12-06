package ru.tutu.stations.ui.activity.main.adapter.model

/**
 * @author Grigoriy Pryamov.
 */
class ShortCountry {
    /**
     * Id
     */
    var id = 0L
    /**
     * Наименование региона - страны
     */
    var countryTitle = ""
    /**
     * Описание
     */
    var districtTitle = ""
    /**
     * открыт/ закрыт параметр для адаптера
     */
    var open = false
    /**
     *
     */
    var stations = ArrayList<ShortStation>()
}