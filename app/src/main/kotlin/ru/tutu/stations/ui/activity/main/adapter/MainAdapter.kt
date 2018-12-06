package ru.tutu.stations.ui.activity.main.adapter

import ru.tutu.stations.ui.activity.main.adapter.model.ShortCountry

/**
 * @author Grigoriy Pryamov.
 */
interface MainAdapter {
    fun setCountries(countries: List<ShortCountry>)
}