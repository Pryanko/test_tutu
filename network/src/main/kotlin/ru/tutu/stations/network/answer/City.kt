package ru.tutu.stations.network.answer

/**
 * @author Grigoriy Pryamov.
 */
class City {
    /**
     * Наименование страны
     */
    val countryTitle = ""
    /**
     * Местоположение
     */
    val point = Point()
    /**
     * Описание наименования
     */
    val districtTitle = ""
    /**
     * Id страны/города
     */
    val cityId = 0L
    /**
     * Наименование города
     */
    val cityTitle = ""
    /**
     * Наименование региона
     */
    val regionTitle = ""
    /**
     * Список станций
     */
    val stations: List<City> = arrayListOf()
    /**
     * Id станции
     */
    val stationId = 0L
    /**
     * Наименование станции
     */
    val stationTitle = ""
}