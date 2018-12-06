package ru.tutu.stations.ui.activity.main.adapter

import ru.tutu.stations.ui.activity.main.adapter.model.ShortCountry
import ru.tutu.stations.ui.activity.main.adapter.model.ShortStation

/**
 * @author Grigoriy Pryamov.
 */
class AdapterData(
    /**
     * Список регионов
     */
    private val countries: List<ShortCountry>
) : ArrayList<Any>() {

    init {
        countries.forEach { country ->
            add(country)
            if (country.open) addAll(country.stations)
        }
    }

    fun countryItem(position: Int): ShortCountry = get(position) as ShortCountry

    fun stationItem(position: Int): ShortStation = get(position) as ShortStation

    fun isCountryItem(position: Int): Boolean = get(position) is ShortCountry

    fun isStationItem(position: Int): Boolean = get(position) is ShortStation

    fun getItemId(position: Int): Long {
        return when {
            isCountryItem(position) -> countryItem(position).id
            isStationItem(position) -> stationItem(position).id
            else -> throw IllegalArgumentException("Unsupported item id")
        }
    }

    fun getList(): List<ShortCountry> = countries
}