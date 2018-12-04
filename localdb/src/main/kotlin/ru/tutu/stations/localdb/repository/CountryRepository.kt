package ru.tutu.stations.localdb.repository

import ru.tutu.stations.localdb.model.Country
import ru.tutu.stations.localdb.repository.base.ModelRepository

/**
 * @author Grigoriy Pryamov.
 */
interface CountryRepository : ModelRepository<Country, Long> {
    fun getAllCountries(): List<Country>
    // Не корректно чекать -по стринг записи, но в рамках тест задания это возможно
    fun containsCountry(nameToLower: String): Boolean
}